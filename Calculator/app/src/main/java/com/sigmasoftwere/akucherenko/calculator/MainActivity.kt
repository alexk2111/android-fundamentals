package com.sigmasoftwere.akucherenko.calculator

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.sigmasoftwere.akucherenko.calculator.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var operationTextView: TextView
    private var resultCalculate: Double = 0.0
    private var operation: Double = 0.0
    private var lastOperation: String = ""
    private var lastButton: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        resultTextView = binding.resultCalculate
        operationTextView = binding.operationCalculate

        resultTextView.text = ""
        operationTextView.text = ""
        lastOperation = "="
        operation = 0.0
        resultCalculate = 0.0
    }

    fun onNumberClick(view: android.view.View) {
        val button: Button = view as Button
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resultTextView.setTextAppearance(R.style.normalStyle)
        }
        if (lastButton != "number") {
            resultTextView.text = ""
        }
        resultTextView.text = resultTextView.text.toString() + button.text.toString()
        lastButton = "number"
    }

    fun onDotClick(view: android.view.View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resultTextView.setTextAppearance(R.style.normalStyle)
        }
        if (lastButton != "number") resultTextView.text = ""
        if (resultTextView.text.toString().lastIndexOf(".") != -1) {
            return
        }
        if (resultTextView.text.toString() == "") {
            resultTextView.text = resultTextView.text.toString() + "0"
        }
        resultTextView.text = resultTextView.text.toString() + "."
        operation = resultTextView.text.toString().toDouble()
        lastButton = "number"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resultTextView.setTextAppearance(R.style.normalStyle)
        }
    }

    fun onOperationClick(view: android.view.View) {
        val button: Button = view as Button
        val currentOperation = button.text.toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resultTextView.setTextAppearance(R.style.normalStyle)
        }
        if (lastOperation == "=") {
            operationTextView.text = resultTextView.text
            lastOperation = currentOperation
            lastButton = "operation"
            return
        }
        performOperation(currentOperation)
        lastButton = "operation"
    }

    fun onEqualsClick(view: android.view.View) {
        val button: Button = view as Button
        val currentOperation = button.text.toString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resultTextView.setTextAppearance(R.style.equalsStyle)
        }

        if (lastOperation == "=") {
            lastButton = "operation"
            return
        }

        val operationTextViewTemp = operationTextView.text.toString() +
                lastOperation + resultTextView.text + currentOperation
        performOperation(currentOperation)
        operationTextView.text = operationTextViewTemp
        lastOperation = currentOperation
        lastButton = "operation"
    }

    private fun performOperation(currentOperation: String) {
        if (lastOperation == "/" && resultTextView.text.toString().toDouble() == 0.0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                resultTextView.setTextAppearance(R.style.errorStyle)
            }
            lastOperation = "="
            resultTextView.text = "Error"
            operationTextView.text = ""
            return
        }
        when (lastOperation) {
            "/" ->
                operationTextView.text =
                    (operationTextView.text.toString().toDouble() / resultTextView.text.toString()
                        .toDouble()).toString()
            "*" ->
                operationTextView.text =
                    (operationTextView.text.toString().toDouble() * resultTextView.text.toString()
                        .toDouble()).toString()
            "-" ->
                operationTextView.text =
                    (operationTextView.text.toString().toDouble() - resultTextView.text.toString()
                        .toDouble()).toString()
            "+" ->
                operationTextView.text =
                    (operationTextView.text.toString().toDouble() + resultTextView.text.toString()
                        .toDouble()).toString()
        }
        resultTextView.text = operationTextView.text
        lastOperation = currentOperation
    }
}