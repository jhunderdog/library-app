package com.group.libraryapp.calculator

import java.lang.Exception

fun main(){
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.devideTest()
    calculatorTest.devideExceptionTest()
}
class CalculatorTest {

    fun addTest(){

        //given
        val calculator = Calculator(5)

        //when
        calculator.add(3)

        //then
        if (calculator.number != 8){
            throw IllegalStateException()
        }
    }

    fun minusTest(){
        //given
        val calculator = Calculator(5)

        //when
        calculator.minus(3)

        //then
        if (calculator.number != 2){
            throw IllegalStateException()
        }

    }

    fun multiplyTest(){
        //given
        val calculator = Calculator(5)

        //when
        calculator.multiply(3)

        //then
        if (calculator.number != 15){
            throw IllegalStateException()
        }

    }

    fun devideTest(){
        //given
        val calculator = Calculator(5)

        //when
        calculator.divide(2)

        //then
        if (calculator.number != 2){
            throw IllegalStateException()
        }

    }

    fun devideExceptionTest(){

        val calculator = Calculator(5)

        try {
             calculator.divide(0)
        } catch (e: IllegalArgumentException){
            return
        } catch (e: Exception){
            throw IllegalStateException()
        }
        throw IllegalStateException("기대하는 예외가 발생하지 않았습니다.")
    }


}