package com.santos.awesomemovieapp


import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
class CalculatorTest {

    lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @Test
    fun sum_whenReceivingNumber2And3_thenResult5() {
        //Given
        val number1 = 2
        val number2 = 3

        //When
        val result = calculator.sum(number1, number2)

        //Then
        assertThat(result).isEqualTo(5)
    }

    @Test
    fun multiply_whenReceivingNumber2And3_thenResult6() {
        //Given
        val number1 = 2
        val number2 = 3

        //When
        val result = calculator.multiply(number1, number2)

        //Then
        assertThat(result).isEqualTo(6)
    }
}