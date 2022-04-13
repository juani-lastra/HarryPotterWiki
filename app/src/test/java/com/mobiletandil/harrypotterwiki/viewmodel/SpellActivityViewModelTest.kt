package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.domain.usecase.GetAllSpellsUseCase
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
class SpellActivityViewModelTest {
    private lateinit var viewModel: SpellsActivityViewModel
    private val getSpellsUseCase: GetAllSpellsUseCase = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SpellsActivityViewModel(getSpellsUseCase)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `InitUi - success state`() {
        val liveData = viewModel.liveData().testObserver()
        val successResult: ResponseResult.Success<List<Spell>> = mock()
        val spells: List<Spell> = mock()

        whenever(successResult.data).thenReturn(spells)
        whenever(getSpellsUseCase()).thenReturn(successResult)
        runBlocking {
            viewModel.initUI().join()
        }
        assertEquals(
            SpellsActivityStatus.INIT_UI,
            liveData.observedValues.first()?.peekContent()?.statusType
        )

        assertEquals(
            spells,
            liveData.observedValues.first()?.peekContent()?.listOfSpells
        )
    }

    @Test
    fun `InitUi - empty state`() {
        val liveData = viewModel.liveData().testObserver()
        val successResult: ResponseResult.Success<List<Spell>> = mock()
        val spells: List<Spell> = emptyList()

        whenever(successResult.data).thenReturn(spells)
        whenever(getSpellsUseCase()).thenReturn(successResult)
        runBlocking {
            viewModel.initUI().join()
        }
        assertEquals(
            SpellsActivityStatus.EMPTY_STATE,
            liveData.observedValues.first()?.peekContent()?.statusType
        )
    }

    @Test
    fun `InitUi - failure state`() {
        val liveData = viewModel.liveData().testObserver()
        val failureResult: ResponseResult.Failure = mock()

        whenever(getSpellsUseCase()).thenReturn(failureResult)
        runBlocking {
            viewModel.initUI().join()
        }
        assertEquals(
            SpellsActivityStatus.ERROR_STATE,
            liveData.observedValues.first()?.peekContent()?.statusType
        )
    }
}
