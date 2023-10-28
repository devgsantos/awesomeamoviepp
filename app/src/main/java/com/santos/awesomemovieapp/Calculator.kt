package com.santos.awesomemovieapp


enum class Operations {
    SUM,
    MULTIPLY
}

class Calculator {

    fun operation(operations: Operations, number1: Int, number2: Int) =
        when(operations) {
            Operations.SUM -> sum(number1, number2)
            Operations.MULTIPLY -> multiply(number1, number2)
        }
    fun sum(number1: Int, number2: Int) = number1 + number2
    fun multiply(number1: Int, number2: Int) = number1 * number2
}