package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.domain.usecase.GetOneSpellUseCase
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
class SpellDetailActivityViewModelTest {
    private lateinit var viewModel: SpellDetailActivityViewModel
    private val getOneSpellUseCase: GetOneSpellUseCase = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SpellDetailActivityViewModel(getOneSpellUseCase)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `initUI - success state`() {
        val liveData = viewModel.liveData().testObserver()
        val successResult: ResponseResult.Success<Spell> = mock()
        val spell = Spell(id = SPELL_ID, EMPTY_STRING, null, null, null, null, null, null)

        whenever(successResult.data).thenReturn(spell)
        runBlocking {
            whenever(getOneSpellUseCase(SPELL_ID)).thenReturn(successResult)
            viewModel.initUI(SPELL_ID).join()
        }
        assertEquals(
            SpellDetailActivityStatus.INIT_UI,
            liveData.observedValues.first()?.peekContent()?.statusType
        )

        assertEquals(
            spell,
            liveData.observedValues.first()?.peekContent()?.data
        )
    }

    @Test
    fun `initUI - empty state`() {
        val liveData = viewModel.liveData().testObserver()
        val successResult: ResponseResult.Success<Spell> = mock()
        val spell = Spell(id = EMPTY_STRING, EMPTY_STRING, null, null, null, null, null, null)

        whenever(successResult.data).thenReturn(spell)
        whenever(getOneSpellUseCase(SPELL_ID)).thenReturn(successResult)
        runBlocking {
            viewModel.initUI(SPELL_ID).join()
        }
        assertEquals(
            SpellDetailActivityStatus.EMPTY_STATE,
            liveData.observedValues.first()?.peekContent()?.statusType
        )
    }

    @Test
    fun `initUI - failure state`() {
        val liveData = viewModel.liveData().testObserver()
        val failureResult: ResponseResult.Failure = mock()

        whenever(getOneSpellUseCase(SPELL_ID)).thenReturn(failureResult)
        runBlocking {
            viewModel.initUI(SPELL_ID).join()
        }
        assertEquals(
            SpellDetailActivityStatus.ERROR_STATE,
            liveData.observedValues.first()?.peekContent()?.statusType
        )
    }

    companion object {
        const val SPELL_ID = "spell id"
        const val EMPTY_STRING = ""
    }
}
