package com.mobiletandil.harrypotterwiki.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.usecase.GetOneSpellUseCase
import com.mobiletandil.domain.usecase.GetOneSpellUseCaseImpl
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
class GetOneSpellsUseCaseTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getOneSpellUseCase: GetOneSpellUseCase
    private val database: HarryPotterDatabase = mock()

    @Before
    fun init() {
        getOneSpellUseCase = GetOneSpellUseCaseImpl(database)
    }

    @Test
    fun `get one spell no-id`() {
        val spell = Spell(EMPTY_STRING, EMPTY_STRING, null, null, null, null, null, null)

        whenever(database.getOneSpell(EMPTY_STRING)).thenReturn(spell)
        val result = getOneSpellUseCase(EMPTY_STRING)
        verify(database).getOneSpell(EMPTY_STRING)
        Assert.assertEquals(spell, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get one spell success`() {
        val spell: Spell = mock()

        whenever(database.getOneSpell(CORRECT_ID)).thenReturn(spell)
        val result = getOneSpellUseCase(CORRECT_ID)
        verify(database).getOneSpell(EMPTY_STRING)
        assertEquals(spell, (result as ResponseResult.Success).data)
    }

    companion object {
        const val CORRECT_ID = ""
        const val EMPTY_STRING = ""
    }
}
