package com.mobiletandil.harrypotterwiki.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.Wizard
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.service.HarryPotterService
import com.mobiletandil.domain.usecase.GetAllWizardsUseCase
import com.mobiletandil.domain.usecase.GetAllWizardsUseCaseImpl
import com.mobiletandil.domain.utils.ResponseResult
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class GetAllWizardsUseCaseTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getOneWizardUseCase: GetAllWizardsUseCase
    private val service: HarryPotterService = mock()
    private val database: HarryPotterDatabase = mock()

    @Before
    fun init() {
        getOneWizardUseCase = GetAllWizardsUseCaseImpl(service, database)
    }

    @Test
    fun `get all spells - success`() {
        val successResult: ResponseResult.Success<List<Wizard>> = mock()
        val spells: List<Wizard> = mock()

        whenever(successResult.data).thenReturn(spells)
        whenever(service.getWizards()).thenReturn(successResult)
        whenever(database.getAllWizards()).thenReturn(spells)
        val result = getOneWizardUseCase()
        verify(service).getWizards()
        verify(database).insertWizards(spells)
        verify(database).getAllWizards()
        assertEquals(spells, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get all spells - error on service, get from db`() {
        val failureResult: ResponseResult.Failure = mock()
        val spells: List<Wizard> = mock()

        whenever(service.getWizards()).thenReturn(failureResult)
        whenever(database.getAllWizards()).thenReturn(spells)
        val result = getOneWizardUseCase()
        verify(service).getWizards()
        verify(database).getAllWizards()
        assertEquals(spells, (result as ResponseResult.Success).data)
    }
}
