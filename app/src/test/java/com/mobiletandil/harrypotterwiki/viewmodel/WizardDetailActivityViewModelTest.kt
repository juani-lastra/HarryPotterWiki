package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.Wizard
import com.mobiletandil.domain.usecase.GetOneWizardUseCase
import com.mobiletandil.domain.utils.ResponseResult
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WizardDetailActivityViewModelTest {
    private lateinit var viewModel: WizardDetailActivityViewModel
    private val getOneWizardUseCase: GetOneWizardUseCase = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        viewModel = WizardDetailActivityViewModel(getOneWizardUseCase)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `initUI - success state`() {
        val liveData = viewModel.liveData().testObserver()
        val successResult: ResponseResult.Success<Wizard> = mock()
        val wizard = Wizard(id = WIZARD_ID, null, null, null)

        whenever(successResult.data).thenReturn(wizard)
        runBlocking {
            whenever(getOneWizardUseCase(WIZARD_ID)).thenReturn(successResult)
            viewModel.initUI(WIZARD_ID).join()
        }
        assertEquals(
            WizardDetailActivityStatus.INIT_UI,
            liveData.observedValues.first()?.peekContent()?.statusType
        )

        assertEquals(
            wizard,
            liveData.observedValues.first()?.peekContent()?.data
        )
    }

    @Test
    fun `initUI - empty state`() {
        val liveData = viewModel.liveData().testObserver()
        val successResult: ResponseResult.Success<Wizard> = mock()
        val wizard = Wizard("", null, null, null)

        whenever(successResult.data).thenReturn(wizard)
        whenever(getOneWizardUseCase(WIZARD_ID)).thenReturn(successResult)
        runBlocking {
            viewModel.initUI(WIZARD_ID).join()
        }
        assertEquals(
            WizardDetailActivityStatus.EMPTY_STATE,
            liveData.observedValues.first()?.peekContent()?.statusType
        )
    }

    @Test
    fun `initUI - failure state`() {
        val liveData = viewModel.liveData().testObserver()
        val failureResult: ResponseResult.Failure = mock()

        whenever(getOneWizardUseCase(WIZARD_ID)).thenReturn(failureResult)
        runBlocking {
            viewModel.initUI(WIZARD_ID).join()
        }
        assertEquals(
            WizardDetailActivityStatus.ERROR_STATE,
            liveData.observedValues.first()?.peekContent()?.statusType
        )
    }

    companion object {
        const val WIZARD_ID = "wizard id"
    }
}
