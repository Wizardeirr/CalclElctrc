package com.volkankelleci.artbooktesting.util

object Util {
    const val API_KEY="23520173-1a644aa914588dcab8799703f"
    const val BASE_URL="https://pixabay.com/api/"

    fun calculateBill(units: Int): Int {

        if (units <= 100) {
            return units * 5
        }
        else if (units >= 101 && units<=500) {
            return (100 * 5 + (units - 100) * 8)
        }
        else if (units > 500) {
            return (100 * 5 + 100 * 8 + ((units - 200)* 10))
        }

        return 0
    }
    fun callCalc(units:Int){
        if (units!=null){
            calculateBill(units)
        }


    }
}