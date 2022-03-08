package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.utils.Houses.GRYFFINDOR_HOUSE
import com.mobiletandil.domain.utils.Houses.HUFFLEPUFF_HOUSE
import com.mobiletandil.domain.utils.Houses.RAVENCLAW_HOUSE
import com.mobiletandil.domain.utils.Houses.SLYTHERIN_HOUSE
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = MainActivityViewModel()

    @Test
    fun `goToHousesActivity - Gryffindor House`() {
        viewModel.goToHousesActivity(GRYFFINDOR_HOUSE)
        assertEquals(
            MainActivityStatus.GO_TO_HOUSES_ACTIVITY,
            viewModel.liveData().value?.peekContent()?.statusType
        )
        assertEquals(
            GRYFFINDOR_HOUSE,
            viewModel.liveData().value?.peekContent()?.house
        )
    }

    @Test
    fun `goToHousesActivity - Slytherin House`() {
        viewModel.goToHousesActivity(SLYTHERIN_HOUSE)
        assertEquals(
            MainActivityStatus.GO_TO_HOUSES_ACTIVITY,
            viewModel.liveData().value?.peekContent()?.statusType
        )
        assertEquals(
            SLYTHERIN_HOUSE,
            viewModel.liveData().value?.peekContent()?.house
        )
    }

    @Test
    fun `goToHousesActivity - Hufflepuff House`() {
        viewModel.goToHousesActivity(HUFFLEPUFF_HOUSE)
        assertEquals(
            MainActivityStatus.GO_TO_HOUSES_ACTIVITY,
            viewModel.liveData().value?.peekContent()?.statusType
        )
        assertEquals(
            HUFFLEPUFF_HOUSE,
            viewModel.liveData().value?.peekContent()?.house
        )
    }

    @Test
    fun `goToHousesActivity - Ravenclaw House`() {
        viewModel.goToHousesActivity(RAVENCLAW_HOUSE)
        assertEquals(
            MainActivityStatus.GO_TO_HOUSES_ACTIVITY,
            viewModel.liveData().value?.peekContent()?.statusType
        )
        assertEquals(
            RAVENCLAW_HOUSE,
            viewModel.liveData().value?.peekContent()?.house
        )
    }

    @Test
    fun goToSpellsActivity() {
        viewModel.goToWizardsActivity()
        assertEquals(
            MainActivityStatus.GO_TO_WIZARDS_ACTIVITY,
            viewModel.liveData().value?.peekContent()?.statusType
        )
    }

    @Test
    fun goToWizardsActivity() {
        viewModel.goToSpellsActivity()
        assertEquals(
            MainActivityStatus.GO_TO_SPELLS_ACTIVITY,
            viewModel.liveData().value?.peekContent()?.statusType
        )
    }
}
