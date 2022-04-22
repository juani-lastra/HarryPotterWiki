package com.mobiletandil.harrypotterwiki.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.service.HarryPotterService
import com.mobiletandil.domain.usecase.GetAllSpellsUseCase
import com.mobiletandil.domain.usecase.GetAllSpellsUseCaseImpl
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
class GetAllSpellsUseCaseTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getOneSpellUseCase: GetAllSpellsUseCase
    private val service: HarryPotterService = mock()
    private val database: HarryPotterDatabase = mock()

    @Before
    fun init() {
        getOneSpellUseCase = GetAllSpellsUseCaseImpl(service, database)
    }

    @Test
    fun `get all spells - success`() {
        val successResult: ResponseResult.Success<List<Spell>> = mock()
        val spells: List<Spell> = mock()

        whenever(successResult.data).thenReturn(spells)
        whenever(service.getSpells()).thenReturn(successResult)
        whenever(database.getSpells()).thenReturn(spells)
        val result = getOneSpellUseCase()
        verify(service).getSpells()
        verify(database).insertSpells(spells)
        verify(database).getSpells()
        assertEquals(spells, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get all spells - error on service, get from db`() {
        val failureResult: ResponseResult.Failure = mock()
        val spells: List<Spell> = mock()

        whenever(service.getSpells()).thenReturn(failureResult)
        whenever(database.getSpells()).thenReturn(spells)
        val result = getOneSpellUseCase()
        verify(service).getSpells()
        verify(database).getSpells()
        assertEquals(spells, (result as ResponseResult.Success).data)
    }
}
