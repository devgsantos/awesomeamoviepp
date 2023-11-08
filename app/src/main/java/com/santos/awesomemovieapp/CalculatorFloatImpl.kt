package com.santos.awesomemovieapp

class CalculatorFloatImpl: CalculatorInterface<Float> {
    override fun sum(number1: Float, number2: Float): Float = number1 + number2
    override fun multiply(number1: Float, number2: Float): Float = number1 * number2
}