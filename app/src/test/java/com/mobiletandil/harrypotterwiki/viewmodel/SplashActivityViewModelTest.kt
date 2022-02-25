package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
class SplashActivityViewModelTest {
    private val viewModel = SplashActivityViewModel()

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun loadMainActivityTest() {
        runBlocking {
            viewModel.loadMainActivity().join()
        }
        assertEquals(
            Status.LOAD_COMPLETE,
            viewModel.liveData().value?.peekContent()?.statusType
        )
    }
}
