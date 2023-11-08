package com.santos.awesomemovieapp


class CalculatorIntImpl: CalculatorInterface<Int> {
    override fun sum(number1: Int, number2: Int) = number1 + number2
    override fun multiply(number1: Int, number2: Int) = number1 * number2
}