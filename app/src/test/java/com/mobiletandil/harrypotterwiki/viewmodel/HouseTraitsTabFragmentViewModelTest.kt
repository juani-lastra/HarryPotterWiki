package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.House
import com.nhaarman.mockitokotlin2.mock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class HouseTraitsTabFragmentViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = HouseTraitsTabFragmentViewModel()

    @Test
    fun initUI() {
        viewModel.initUI(MOCK_HOUSE_OK)
        assertEquals(
            HouseTraitsStatus.INIT_UI,
            viewModel.liveData().value?.peekContent()?.statusType
        )
        assertEquals(
            MOCK_HOUSE_OK,
            viewModel.liveData().value?.peekContent()?.houseData
        )
    }

    companion object {
        val MOCK_HOUSE_OK: House = House(
            id = "",
            name = "ValidName",
            houseColours = "",
            founder = "",
            animal = "",
            element = "",
            ghost = "",
            commonRoom = "",
            heads = mock(),
            traits = mock()
        )
    }
}
