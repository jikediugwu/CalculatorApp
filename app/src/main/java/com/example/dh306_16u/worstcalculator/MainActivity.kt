package com.example.dh306_16u.worstcalculator

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


enum class CalculatorMode {
    None, Multiply, Add, Subtract, Divide
} // enum class that holds the calculation mode

class MainActivity : Activity() {

    var lastButtonWasMode = false
    var currentmode = CalculatorMode.None
    var labelString = ""
    var savedNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCalculator()

    } // end of override function

    fun setupCalculator() {
        val allButtons = arrayOf(button0,button1,button2,button3,button4,button5,button6,button7,button8,button9)
        for(i in allButtons.indices) {
            allButtons[i].setOnClickListener { didPressNumber(i) }
        }
        buttonTimes.setOnClickListener { changeMode(CalculatorMode.Multiply) }
        buttonAdd.setOnClickListener { changeMode(CalculatorMode.Add) }
        buttonSubtract.setOnClickListener { changeMode(CalculatorMode.Subtract) }
        buttonDivide.setOnClickListener { changeMode(CalculatorMode.Divide) }
        buttonEquals.setOnClickListener { didPressEquals() }
        buttonClear.setOnClickListener { didPressClear() }

    } // end of setupCalculation function that executes the calculation

    fun didPressEquals() {

        if(lastButtonWasMode){
            return

        } // end of if statement

        val labelInt = labelString.toInt()
        when (currentmode) {

            CalculatorMode.Multiply -> savedNumber = savedNumber * labelInt
            CalculatorMode.Add -> savedNumber = savedNumber + labelInt
            CalculatorMode.Subtract -> savedNumber = savedNumber - labelInt
            CalculatorMode.Divide -> savedNumber = savedNumber / labelInt

            CalculatorMode.None -> return
        } // end of when statement
        currentmode = CalculatorMode.None
        labelString = "$savedNumber"
        updateText()
        lastButtonWasMode = true

    } // end of didPressEquals function

    fun didPressClear() {

        lastButtonWasMode = false
        currentmode = CalculatorMode.None
        labelString = ""
        savedNumber = 0
        TextView.text = "0"
    } // end of didPressClear function

    fun updateText() {

        val labelInt = labelString.toInt()
        labelString = labelInt.toString()

        if(currentmode == CalculatorMode.None){
            savedNumber = labelInt

        }
        TextView.text = labelString
    } // end of updateText function

    fun changeMode(mode: CalculatorMode) {
        if(savedNumber == 0){
            return

        }
        currentmode = mode
        lastButtonWasMode =true


    } // end of changeMode function

    fun didPressNumber(num: Int) {

        val stringValue = num.toString()
        if(lastButtonWasMode) {
            lastButtonWasMode = false
            labelString = "0"

        } // end of didPressNumber function

        labelString = "$labelString$stringValue"
        updateText()
    } // end of didPressNumber function

} // end of main activity class