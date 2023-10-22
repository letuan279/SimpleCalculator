package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var canAddOperation = false
    private var isNewOperand = false

    private lateinit var inputTV : TextView
    private lateinit var resultTV : TextView

    private var leftOperand = 0
    private var rightOperand = 0
    private var currOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputTV = findViewById<TextView>(R.id.inputTV)
        resultTV = findViewById<TextView>(R.id.resultTV)
    }

    fun numberAction(view: View)
    {
        if(view is Button)
        {
            if(resultTV.text == "0" || isNewOperand) resultTV.text = ""
            resultTV.append(view.text)
            canAddOperation = true
            isNewOperand = false
        }
    }

    // Hàm chọn phép tính
    fun calButton(view: View) {
        if(view is Button && canAddOperation)
        {
            leftOperand = resultTV.text.toString().toInt()
            inputTV.text = resultTV.text.toString() + view.text.toString()
            canAddOperation = false
            isNewOperand = true
            currOperator = view.text.toString()
        }
    }

    fun ceButton(view: View) {
        resultTV.text = "0"
    }

    fun cButton(view: View) {
        inputTV.text = ""
        resultTV.text = ""
    }

    fun bsButton(view: View) {
        val length = resultTV.length()
        if(length > 1) {
            resultTV.text = resultTV.text.subSequence(0, length - 1)
        } else {
            resultTV.text = "0";
        }
    }

    fun unknowButton(view: View) {
        return;
    }

    fun equalButton(view: View) {
        rightOperand = resultTV.text.toString().toInt()
        inputTV.text = inputTV.text.toString() + resultTV.text.toString() + "="

        var result = 0;
        when(currOperator) {
            "+" -> {
                result = leftOperand + rightOperand
            }
            "-" -> {
                result = leftOperand - rightOperand
            }
            "x" -> {
                result = leftOperand * rightOperand
            }
            "/" -> {
                result = leftOperand / rightOperand
            }
            else -> {
                result = 0
            }
        }

        resultTV.text = result.toString()
    }
}