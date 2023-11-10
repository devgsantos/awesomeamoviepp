package com.santos.awesomemovieapp


import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
class CalculatorIntImplTest {

    lateinit var calculatorIntImpl: CalculatorIntImpl

    @Before
    fun setup() {
        calculatorIntImpl = CalculatorIntImpl()
    }

    @Test
    fun sum_whenReceivingNumber2And3_thenResult5() {
        //Given
        val number1 = 2
        val number2 = 3

        val operation = Operations.SUM

        //When
        val result = calculatorIntImpl.sum(number1, number2)

        //Then
        assertThat(result).isEqualTo(5)
    }

    @Test
    fun multiply_whenReceivingNumber2And3_thenResult6() {
        //Given
        val number1 = 2
        val number2 = 3

        val operation = Operations.MULTIPLY

        //When
        val result = calculatorIntImpl.multiply(number1, number2)

        //Then
        assertThat(result).isEqualTo(6)
    }
}