package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.service.HarryPotterService
import com.mobiletandil.domain.usecase.GetOneHouseUseCase
import com.mobiletandil.domain.usecase.GetOneHouseUseCaseImpl
import com.mobiletandil.domain.utils.Houses
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
class HousesActivityViewModelTest {
    private lateinit var viewModel: HousesActivityViewModel
    private lateinit var getHouseUseCase: GetOneHouseUseCase
    private val service: HarryPotterService = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        getHouseUseCase = GetOneHouseUseCaseImpl(service)
        viewModel = HousesActivityViewModel(getHouseUseCase)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `setTabs - success state`() {
        val liveData = viewModel.liveData().testObserver()
        val successResult: ResponseResult.Success<House> = mock()
        val house: House = HOUSE_MOCK

        whenever(successResult.data).thenReturn(house)
        whenever(getHouseUseCase(Houses.GRYFFINDOR_HOUSE)).thenReturn(successResult)
        runBlocking {
            viewModel.setTabs(Houses.GRYFFINDOR_HOUSE).join()
        }
        assertEquals(
            HousesActivityStatus.SET_TABS,
            liveData.observedValues.first()?.peekContent()?.statusType
        )

        assertEquals(
            house,
            liveData.observedValues.first()?.peekContent()?.houseData
        )
    }

    @Test
    fun `setTabs - failure state, empty data`() {
        val liveData = viewModel.liveData().testObserver()
        val successResult: ResponseResult.Success<House> = mock()
        val house: House = HOUSE_MOCK_EMPTY

        whenever(successResult.data).thenReturn(house)
        whenever(getHouseUseCase(Houses.GRYFFINDOR_HOUSE)).thenReturn(successResult)
        runBlocking {
            viewModel.setTabs(Houses.GRYFFINDOR_HOUSE).join()
        }
        assertEquals(
            HousesActivityStatus.EMPTY_STATE,
            liveData.observedValues.first()?.peekContent()?.statusType
        )
    }

    @Test
    fun `setTabs - failure state`() {
        val liveData = viewModel.liveData().testObserver()
        val failureResult: ResponseResult.Failure = mock()

        whenever(getHouseUseCase(Houses.GRYFFINDOR_HOUSE)).thenReturn(failureResult)
        runBlocking {
            viewModel.setTabs(Houses.GRYFFINDOR_HOUSE).join()
        }
        assertEquals(
            HousesActivityStatus.ERROR_STATE,
            liveData.observedValues.first()?.peekContent()?.statusType
        )
    }

    companion object {
        private val HOUSE_MOCK: House = House(
            id = "",
            name = "ValidName",
            houseColours = "",
            founder = "",
            animal = "",
            element = "",
            ghost = "",
            commonRoom = "",
            heads = emptyList(),
            traits = emptyList()
        )
        private val HOUSE_MOCK_EMPTY: House = House(
            id = "",
            name = "",
            houseColours = "",
            founder = "",
            animal = "",
            element = "",
            ghost = "",
            commonRoom = "",
            heads = emptyList(),
            traits = emptyList()
        )
    }
}
