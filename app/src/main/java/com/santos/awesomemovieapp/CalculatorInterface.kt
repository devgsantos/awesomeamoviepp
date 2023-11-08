package com.santos.awesomemovieapp

interface CalculatorInterface<T> {
    fun sum(number1: T, number2: T): T
    fun multiply(number1: T, number2: T): T
}