package com.example.dh306_16u.worstcalculator

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


enum class CalculatorMode {
    None, Add, Subtract
}

class MainActivity : Activity() {

    var lastButtonWasMode = false
    var currentmode = CalculatorMode.None
    var labelString = ""
    var savedNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCalculator()

    }

    fun setupCalculator() {
        val allButtons = arrayOf(button0,button1,button2,button3,button4,button5,button6,button7,button8,button9)
        for(i in allButtons.indices) {
            allButtons[i].setOnClickListener { didPressNumber(i) }
        }
        buttonPlus.setOnClickListener { changeMode(CalculatorMode.Add) }
        buttonMinus.setOnClickListener { changeMode(CalculatorMode.Subtract) }
        buttonEquals.setOnClickListener { didPressEquals() }
        buttonClear.setOnClickListener { didPressClear() }

    }

    fun didPressEquals() {

        if(lastButtonWasMode){
            return

        }

        val labelInt = labelString.toInt()
        when (currentmode) {
            CalculatorMode.Add -> savedNumber = savedNumber + labelInt
            CalculatorMode.Subtract -> savedNumber = savedNumber - labelInt
            CalculatorMode.None -> return
        }
        currentmode = CalculatorMode.None
        labelString = "$savedNumber"
        updateText()
        lastButtonWasMode = true

    }

    fun didPressClear() {

        lastButtonWasMode = false
        currentmode = CalculatorMode.None
        labelString = ""
        savedNumber = 0
        TextView.text = "0"
    }

    fun updateText() {

        val labelInt = labelString.toInt()
        labelString = labelInt.toString()

        if(currentmode == CalculatorMode.None){
            savedNumber = labelInt

        }
        TextView.text = labelString
    }

    fun changeMode(mode: CalculatorMode) {
        if(savedNumber == 0){
            return

        }
        currentmode = mode
        lastButtonWasMode =true


    }

    fun didPressNumber(num: Int) {

        val stringValue = num.toString()
        if(lastButtonWasMode) {
            lastButtonWasMode = false
            labelString = "0"

        }

        labelString = "$labelString$stringValue"
        updateText()
    }

}
