package com.example.tipcalculator.viewodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tipcalculator.model.Tip
import com.example.tipcalculator.model.Tip_Calculator


class TipCalculatorViewModel : ViewModel() {

    companion object{
        val calc: Tip_Calculator = Tip_Calculator()
    }
    var tip1 : MutableLiveData<Tip> = MutableLiveData()

    var tip : Tip = Tip()


    fun tip(tippercentage: Double,tipamount: Double){
        tip = calc.calculateTip(tipamount,tippercentage)
        tip1.postValue(tip)
    }
}
