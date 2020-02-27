package com.example.tipcalculator.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Tip_Calculator {

     var tip : Tip = Tip()

    fun calculateTip(checkAmount:Double, tipPercentage:Double) : Tip{
        var tipAmount = (checkAmount*tipPercentage)/100
        var totalAmount = checkAmount + tipAmount
        val tip1 = Tip(checkAmount,tipPercentage,tipAmount,totalAmount)
        println("from tipvcalculator"+tip1)



        return tip1
    }
}