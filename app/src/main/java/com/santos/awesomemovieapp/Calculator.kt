package com.santos.awesomemovieapp

import kotlin.jvm.Throws

enum class Operations {
    SUM,
    MULTIPLY
}

class Calculator {
    private val calcInt = CalculatorIntImpl()
    private val calcFloat = CalculatorFloatImpl()

    fun calculate(operation: Operations, number1: Number, number2: Number): Number {
        return if (number1 is Int && number2 is Int) {
            when(operation) {
                Operations.SUM -> calcInt.sum(number1, number2)
                Operations.MULTIPLY -> calcInt.multiply(number1, number2)
            }
        } else if(number1 is Float && number2 is Float) {
            when(operation) {
                Operations.SUM -> calcFloat.sum(number1, number2)
                Operations.MULTIPLY -> calcFloat.multiply(number1, number2)
            }
        } else {
            throw Exception("Invalid operation")
        }
    }
}