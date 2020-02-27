package com.example.tipcalculator.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.tipcalculator.R
import com.example.tipcalculator.viewodel.TipCalculatorViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.tip_calculator_fragment.*
import kotlinx.android.synthetic.main.tip_calculator_fragment.view.*


class TipCalculator : Fragment() {

    companion object {
        fun newInstance() = TipCalculator()
    }

    private lateinit var viewModel: TipCalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        return inflater.inflate(R.layout.tip_calculator_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TipCalculatorViewModel::class.java)

        tip_percentage.setText(toggleButtonGroup.findViewById<MaterialButton>(toggleButtonGroup.checkedButtonId).text)

        tip_percentage.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                val lol = tip_percentage.text.toString()
                if (lol == "10"){
                    toggleButtonGroup.check(R.id.btn10)
                    calculate_total()

                }
                else if (lol == "12"){
                    toggleButtonGroup.check(R.id.btn12)
                    calculate_total()

                }
                else if (lol == "15"){
                    toggleButtonGroup.check(R.id.btn15)
                    calculate_total()

                }
                else if (lol == "18"){
                    toggleButtonGroup.check(R.id.btn18)
                    calculate_total()

                }
                else if (lol == "20"){
                    toggleButtonGroup.check(R.id.btn20)
                    calculate_total()

                }
                else{
                    println("cleared")
                    toggleButtonGroup.isSelectionRequired = false
                    toggleButtonGroup.uncheck(toggleButtonGroup.checkedButtonId)
                    calculate_total()

                }
                true
            }
            false
        }

        bill_amount.setOnEditorActionListener{ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){

                calculate_total()
                true
            }
            false
        }

        toggleButtonGroup.addOnButtonCheckedListener { group, checkedid, ischecked ->
            if (ischecked == true && checkedid != -1) {
                toggleButtonGroup.isSelectionRequired = true
                println("checked" + checkedid)
                println("unchecked" + ischecked)
                val btn: MaterialButton = group.findViewById(checkedid)
                val tippercentage = btn.text.toString()
                tip_percentage.setText(tippercentage)

                calculate_total()

            }
            else{
                println("unchecked" + checkedid)
                println("unchecked" + ischecked)
            }
        }

        viewModel.tip1.observe(viewLifecycleOwner, Observer {
            tip_value.setText(it.tipAmount.toString())
            total_value.setText(it.totalAmount.toString())


        })






    }

    private fun calculate_total() {

        if(bill_amount.text.toString().equals("")){
           val snack = Snackbar.make(requireView(),"bill amount is empty",Snackbar.LENGTH_LONG)
            snack.show()

        }
        else if(tip_percentage.text.toString().equals("")) {

            viewModel.tip(
                0.0,
                bill_amount.text.toString().toDouble()
            )

        }
        else {
            viewModel.tip(
                tip_percentage.text.toString().toDouble(),
                bill_amount.text.toString().toDouble()
            )
        }


    }

}