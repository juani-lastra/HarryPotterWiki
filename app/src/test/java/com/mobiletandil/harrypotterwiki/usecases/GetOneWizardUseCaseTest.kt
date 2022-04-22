package com.mobiletandil.harrypotterwiki.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobiletandil.domain.entity.Wizard
import com.mobiletandil.domain.service.HarryPotterDatabase
import com.mobiletandil.domain.usecase.GetOneWizardUseCase
import com.mobiletandil.domain.usecase.GetOneWizardUseCaseImpl
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
class GetOneWizardUseCaseTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getOneWizardUseCase: GetOneWizardUseCase
    private val database: HarryPotterDatabase = mock()

    @Before
    fun init() {
        getOneWizardUseCase = GetOneWizardUseCaseImpl(database)
    }

    @Test
    fun `get one wizard no-id`() {
        val wizard = Wizard(EMPTY_STRING, EMPTY_STRING, null, null)

        whenever(database.getOneWizard(EMPTY_STRING)).thenReturn(wizard)
        val result = getOneWizardUseCase(EMPTY_STRING)
        verify(database).getOneWizard(EMPTY_STRING)
        Assert.assertEquals(wizard, (result as ResponseResult.Success).data)
    }

    @Test
    fun `get one wizard success`() {
        val wizard: Wizard = mock()

        whenever(database.getOneWizard(CORRECT_ID)).thenReturn(wizard)
        val result = getOneWizardUseCase(CORRECT_ID)
        verify(database).getOneWizard(EMPTY_STRING)
        assertEquals(wizard, (result as ResponseResult.Success).data)
    }

    companion object {
        const val CORRECT_ID = ""
        const val EMPTY_STRING = ""
    }
}
