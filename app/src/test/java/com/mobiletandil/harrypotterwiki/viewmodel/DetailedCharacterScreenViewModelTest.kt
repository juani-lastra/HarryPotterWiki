package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.HouseHead
import com.mobiletandil.domain.entity.Spells
import com.mobiletandil.domain.entity.Wizards
import com.nhaarman.mockitokotlin2.mock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class DetailedCharacterScreenViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = DetailedCharacterScreenViewModel()

    @Test
    fun `initUI with head`() {
        val head: HouseHead = mock()
        viewModel.initUI(head, null, null)
        assertEquals(
            DetailedCharacterStatus.INIT_UI_HEAD,
            viewModel.liveData().value?.peekContent()?.statusType
        )
        assertEquals(
            head,
            viewModel.liveData().value?.peekContent()?.headData
        )
    }

    @Test
    fun `initUI with wizard`() {
        val wizard: Wizards = mock()
        viewModel.initUI(null, wizard, null)
        assertEquals(
            DetailedCharacterStatus.INIT_UI_WIZARD,
            viewModel.liveData().value?.peekContent()?.statusType
        )
        assertEquals(
            wizard,
            viewModel.liveData().value?.peekContent()?.wizardData
        )
    }

    @Test
    fun `initUI with spell`() {
        val spell: Spells = mock()
        viewModel.initUI(null, null, spell)
        assertEquals(
            DetailedCharacterStatus.INIT_UI_SPELL,
            viewModel.liveData().value?.peekContent()?.statusType
        )
        assertEquals(
            spell,
            viewModel.liveData().value?.peekContent()?.spellData
        )
    }

    @Test
    fun `initUI empty state`() {
        viewModel.initUI(null, null, null)
        assertEquals(
            DetailedCharacterStatus.EMPTY_STATE,
            viewModel.liveData().value?.peekContent()?.statusType
        )
    }
}
