package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.Wizards
import com.mobiletandil.domain.usecase.GetAllWizardsUseCase
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
class WizardsActivityViewModelTest {
    private lateinit var viewModel: WizardsActivityViewModel
    private val getWizardsUseCase: GetAllWizardsUseCase = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        viewModel = WizardsActivityViewModel(getWizardsUseCase)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `InitUi - success state`() {
        val liveData = viewModel.liveData().testObserver()
        val successResult: ResponseResult.Success<List<Wizards>> = mock()
        val wizards: List<Wizards> = mock()

        whenever(successResult.data).thenReturn(wizards)
        whenever(getWizardsUseCase()).thenReturn(successResult)
        runBlocking {
            viewModel.initUI().join()
        }
        assertEquals(
            WizardsActivityStatus.INIT_UI,
            liveData.observedValues.first()?.peekContent()?.statusType
        )

        assertEquals(
            wizards,
            liveData.observedValues.first()?.peekContent()?.listOfWizards
        )
    }

    @Test
    fun `setTabs - empty state`() {
        val liveData = viewModel.liveData().testObserver()
        val successResult: ResponseResult.Success<List<Wizards>> = mock()
        val wizards: List<Wizards> = emptyList()

        whenever(successResult.data).thenReturn(wizards)
        whenever(getWizardsUseCase()).thenReturn(successResult)
        runBlocking {
            viewModel.initUI().join()
        }
        assertEquals(
            WizardsActivityStatus.EMPTY_STATE,
            liveData.observedValues.first()?.peekContent()?.statusType
        )
    }

    @Test
    fun `setTabs - failure state`() {
        val liveData = viewModel.liveData().testObserver()
        val failureResult: ResponseResult.Failure = mock()

        whenever(getWizardsUseCase()).thenReturn(failureResult)
        runBlocking {
            viewModel.initUI().join()
        }
        assertEquals(
            WizardsActivityStatus.ERROR_STATE,
            liveData.observedValues.first()?.peekContent()?.statusType
        )
    }
}
