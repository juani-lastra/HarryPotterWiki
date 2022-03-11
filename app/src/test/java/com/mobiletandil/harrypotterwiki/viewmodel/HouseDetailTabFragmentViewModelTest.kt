package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class HouseDetailTabFragmentViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = HouseDetailTabFragmentViewModel()

    @Test
    fun initUI() {
        viewModel.initUI()
        assertEquals(
            HouseDetailStatus.INIT_UI,
            viewModel.liveData().value?.peekContent()?.statusType
        )
    }
}
