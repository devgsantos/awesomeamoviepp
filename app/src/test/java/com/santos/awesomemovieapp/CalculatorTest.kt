package com.santos.awesomemovieapp

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class CalculatorTest {
    @get:Rule
    val mockKRule = MockKRule(this)

    lateinit var calculator: Calculator

    @MockK
    lateinit var calcInt: CalculatorIntImpl

    @MockK
    lateinit var calcFloat: CalculatorFloatImpl

    @Before
    fun setUp() {
        every {
            calcInt.sum(any(), any())
        } returns 0

        every {
            calcInt.multiply(any(), any())
        } returns 0

        every {
            calcFloat.sum(any(), any())
        } returns 0F

        every {
            calcFloat.multiply(any(), any())
        } returns 0F

        calculator = Calculator(calcInt, calcFloat)
    }

    @Test
    fun operation_whenOperationsSum_AndTwoNumbersInt_thenShouldCallCalcIntSum() {
        val number1 = 1
        val number2 = 2
        val operation = Operations.SUM

        calculator.calculate(operation, number1, number2)

        verify(exactly = 1) {
            calcInt.sum(number1, number2)
        }
    }

    @Test
    fun operation_whenOperationsMultiply_AndTwoNumbersInt_thenShouldCallCalcIntMultiply() {
        val number1 = 1
        val number2 = 2
        val operation = Operations.MULTIPLY

        calculator.calculate(operation, number1, number2)

        verify(exactly = 1) {
            calcInt.multiply(number1, number2)
        }
    }

    @Test
    fun operation_whenOperationsSum_AndTwoNumbersFloat_thenShouldCallCalcFloatSum() {
        val number1 = 1.0F
        val number2 = 2.0F
        val operation = Operations.SUM

        calculator.calculate(operation, number1, number2)

        verify(exactly = 1) {
            calcFloat.sum(number1, number2)
        }
    }

    @Test
    fun operation_whenOperationsMultiply_AndTwoNumbersFloat_thenShouldCallCalcFloatMultiply() {
        val number1 = 1.0F
        val number2 = 2.0F
        val operation = Operations.MULTIPLY

        calculator.calculate(operation, number1, number2)

        verify(exactly = 1) {
            calcFloat.multiply(number1, number2)
        }
    }

    @Test(expected = Exception::class)
    fun operation_whenOperationsSum_AndTwoNumbersDouble_thenShouldThrowException() {
        val number1 = 1.0
        val number2 = 2.0
        val operation = Operations.SUM

        calculator.calculate(operation, number1, number2)
    }

    @Test(expected = Exception::class)
    fun operation_whenOperationsMultiply_AndTwoNumbersDoubl_thenShouldThrowException() {
        val number1 = 1.0
        val number2 = 2.0
        val operation = Operations.MULTIPLY

        calculator.calculate(operation, number1, number2)
    }
}