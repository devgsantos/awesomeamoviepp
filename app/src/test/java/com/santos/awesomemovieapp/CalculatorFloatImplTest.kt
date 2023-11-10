package com.santos.awesomemovieapp

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class CalculatorFloatImplTest {
    lateinit var calculatorFloatImpl: CalculatorFloatImpl

    @Before
    fun setup() {
        calculatorFloatImpl = CalculatorFloatImpl()
    }

    @Test
    fun sum_whenReceivingNumber2And3_thenResult5() {
        //Given
        val number1 = 2.0F
        val number2 = 3.0F

        val operation = Operations.SUM

        //When
        val result = calculatorFloatImpl.sum(number1, number2)

        //Then
        Truth.assertThat(result).isEqualTo(5.0F)
    }

    @Test
    fun multiply_whenReceivingNumber2And3_thenResult6() {
        //Given
        val number1 = 2.0F
        val number2 = 3.0F

        val operation = Operations.MULTIPLY

        //When
        val result = calculatorFloatImpl.multiply(number1, number2)

        //Then
        Truth.assertThat(result).isEqualTo(6.0F)
    }
}