package com.mobiletandil.harrypotterwiki.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.service.HarryPotterService
import com.mobiletandil.domain.usecase.GetOneHouseUseCase
import com.mobiletandil.domain.usecase.GetOneHouseUseCaseImpl
import com.mobiletandil.domain.utils.Houses
import com.mobiletandil.domain.utils.Houses.GRYFFINDOR_HOUSE
import com.mobiletandil.domain.utils.Houses.SLYTHERIN_HOUSE
import com.mobiletandil.domain.utils.HousesIds.GRYFFINDOR_HOUSE
import com.mobiletandil.domain.utils.HousesIds.HUFFLEPUFF_HOUSE
import com.mobiletandil.domain.utils.HousesIds.RAVENCLAW_HOUSE
import com.mobiletandil.domain.utils.HousesIds.SLYTHERIN_HOUSE
import com.mobiletandil.domain.utils.ResponseResult
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class GetOneHouseUseCaseTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getOneHouseUseCase: GetOneHouseUseCase
    private val service: HarryPotterService = mock()
    private val database: HarryPotterDatabase = mock()

    @Before
    fun init() {
        getOneHouseUseCase = GetOneHouseUseCaseImpl(service, database)
    }

    @Test
    fun `get one house no-id`() {
        val failureResult: ResponseResult.Failure = mock()
        val house: House = MOCK_HOUSE_EMPTY

        whenever(service.getHouse(EMPTY_STRING)).thenReturn(failureResult)
        whenever(database.getHouse(EMPTY_STRING)).thenReturn(house)
        val result = getOneHouseUseCase(Houses.NONE)
        verify(service).getHouse(EMPTY_STRING)
        verify(database).getHouse(EMPTY_STRING)
        Assert.assertEquals(house, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get one house - gryffindor - success`() {
        val successResult: ResponseResult.Success<House> = mock()
        val house: House = MOCK_HOUSE_OK

        whenever(successResult.data).thenReturn(house)
        whenever(service.getHouse(GRYFFINDOR_HOUSE)).thenReturn(successResult)
        whenever(database.getHouse(GRYFFINDOR_HOUSE)).thenReturn(house)
        val result = getOneHouseUseCase(Houses.GRYFFINDOR_HOUSE)
        verify(service).getHouse(GRYFFINDOR_HOUSE)
        verify(database).insertHouse(house)
        verify(database).getHouse(GRYFFINDOR_HOUSE)
        assertEquals(house, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get one house - gryffindor - success, error on service, get from db`() {
        val failureResult: ResponseResult.Failure = mock()
        val house: House = MOCK_HOUSE_OK

        whenever(service.getHouse(GRYFFINDOR_HOUSE)).thenReturn(failureResult)
        whenever(database.getHouse(GRYFFINDOR_HOUSE)).thenReturn(house)
        val result = getOneHouseUseCase(Houses.GRYFFINDOR_HOUSE)
        verify(service).getHouse(GRYFFINDOR_HOUSE)
        verify(database).getHouse(GRYFFINDOR_HOUSE)
        Assert.assertEquals(house, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get one house - slytherin - success`() {
        val successResult: ResponseResult.Success<House> = mock()
        val house: House = MOCK_HOUSE_OK

        whenever(successResult.data).thenReturn(house)
        whenever(service.getHouse(SLYTHERIN_HOUSE)).thenReturn(successResult)
        whenever(database.getHouse(SLYTHERIN_HOUSE)).thenReturn(house)
        val result = getOneHouseUseCase(Houses.SLYTHERIN_HOUSE)
        verify(service).getHouse(SLYTHERIN_HOUSE)
        verify(database).insertHouse(house)
        verify(database).getHouse(SLYTHERIN_HOUSE)
        assertEquals(house, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get one house - slytherin - success, error on service, get from db`() {
        val failureResult: ResponseResult.Failure = mock()
        val house: House = MOCK_HOUSE_OK

        whenever(service.getHouse(SLYTHERIN_HOUSE)).thenReturn(failureResult)
        whenever(database.getHouse(SLYTHERIN_HOUSE)).thenReturn(house)
        val result = getOneHouseUseCase(Houses.SLYTHERIN_HOUSE)
        verify(service).getHouse(SLYTHERIN_HOUSE)
        verify(database).getHouse(SLYTHERIN_HOUSE)
        Assert.assertEquals(house, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get one house - hufflepuff - success`() {
        val successResult: ResponseResult.Success<House> = mock()
        val house: House = MOCK_HOUSE_OK

        whenever(successResult.data).thenReturn(house)
        whenever(service.getHouse(HUFFLEPUFF_HOUSE)).thenReturn(successResult)
        whenever(database.getHouse(HUFFLEPUFF_HOUSE)).thenReturn(house)
        val result = getOneHouseUseCase(Houses.HUFFLEPUFF_HOUSE)
        verify(service).getHouse(HUFFLEPUFF_HOUSE)
        verify(database).insertHouse(house)
        verify(database).getHouse(HUFFLEPUFF_HOUSE)
        assertEquals(house, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get one house - hufflepuff - success, error on service, get from db`() {
        val failureResult: ResponseResult.Failure = mock()
        val house: House = MOCK_HOUSE_OK

        whenever(service.getHouse(HUFFLEPUFF_HOUSE)).thenReturn(failureResult)
        whenever(database.getHouse(HUFFLEPUFF_HOUSE)).thenReturn(house)
        val result = getOneHouseUseCase(Houses.HUFFLEPUFF_HOUSE)
        verify(service).getHouse(HUFFLEPUFF_HOUSE)
        verify(database).getHouse(HUFFLEPUFF_HOUSE)
        Assert.assertEquals(house, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get one house - ravenclaw - success`() {
        val successResult: ResponseResult.Success<House> = mock()
        val house: House = MOCK_HOUSE_OK

        whenever(successResult.data).thenReturn(house)
        whenever(service.getHouse(RAVENCLAW_HOUSE)).thenReturn(successResult)
        whenever(database.getHouse(RAVENCLAW_HOUSE)).thenReturn(house)
        val result = getOneHouseUseCase(Houses.RAVENCLAW_HOUSE)
        verify(service).getHouse(RAVENCLAW_HOUSE)
        verify(database).insertHouse(house)
        verify(database).getHouse(RAVENCLAW_HOUSE)
        assertEquals(house, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get one house - ravenclaw - success, error on service, get from db`() {
        val failureResult: ResponseResult.Failure = mock()
        val house: House = MOCK_HOUSE_OK

        whenever(service.getHouse(RAVENCLAW_HOUSE)).thenReturn(failureResult)
        whenever(database.getHouse(RAVENCLAW_HOUSE)).thenReturn(house)
        val result = getOneHouseUseCase(Houses.RAVENCLAW_HOUSE)
        verify(service).getHouse(RAVENCLAW_HOUSE)
        verify(database).getHouse(RAVENCLAW_HOUSE)
        Assert.assertEquals(house, (result as ResponseResult.Success).data)
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
        val MOCK_HOUSE_EMPTY: House = House(
            id = "",
            name = "",
            houseColours = "",
            founder = "",
            animal = "",
            element = "",
            ghost = "",
            commonRoom = "",
            heads = mock(),
            traits = mock()
        )
        const val EMPTY_STRING = ""
    }
}
