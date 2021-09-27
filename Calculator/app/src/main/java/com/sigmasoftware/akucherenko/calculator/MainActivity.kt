package com.sigmasoftware.akucherenko.calculator

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import com.sigmasoftware.akucherenko.calculator.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TYPE_BUTTON_NUMBER: String = "number"
    private val TYPE_BUTTON_OPERATION: String = "operation"
    private val ERROR_RESULT: String = "Error"

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
        lastOperation = binding.buttonEquals.text.toString()
        operation = 0.0
        resultCalculate = 0.0
    }

    fun onNumberClick(view: android.view.View) {

        animation(view)

        val button: Button = view as Button

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resultTextView.setTextAppearance(R.style.normalStyle)
        }

        if (lastButton != TYPE_BUTTON_NUMBER) {
            resultTextView.text = ""
        }

        resultTextView.text = resultTextView.text.toString() + button.text.toString()
        lastButton = TYPE_BUTTON_NUMBER

    }

    fun onDotClick(view: android.view.View) {

        animation(view)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resultTextView.setTextAppearance(R.style.normalStyle)
        }

        if (lastButton != TYPE_BUTTON_NUMBER) resultTextView.text = ""

        if (resultTextView.text.toString().lastIndexOf(".") != -1) {
            return
        }

        if (resultTextView.text.toString().isEmpty()) {
            resultTextView.text = resultTextView.text.toString() + "0"
        }

        resultTextView.text = resultTextView.text.toString() + "."
        operation = resultTextView.text.toString().toDouble()
        lastButton = TYPE_BUTTON_NUMBER

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resultTextView.setTextAppearance(R.style.normalStyle)
        }

    }

    fun onOperationClick(view: android.view.View) {

        val button: Button = view as Button
        val currentOperation = button.text.toString()

        animation(view)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resultTextView.setTextAppearance(R.style.normalStyle)
        }

//        if (lastOperation == "=") {
        if (lastOperation == binding.buttonEquals.text.toString()) {
            operationTextView.text = resultTextView.text
            lastOperation = currentOperation
            lastButton = TYPE_BUTTON_OPERATION
            return
        }

        performOperation(currentOperation)

        lastButton = TYPE_BUTTON_OPERATION

    }

    fun onEqualsClick(view: android.view.View) {

        val button: Button = view as Button
        val currentOperation = button.text.toString()

        animation(view)

        val animation: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        resultTextView.startAnimation(animation)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resultTextView.setTextAppearance(R.style.equalsStyle)
        }

//        if (lastOperation == "=") {
        if (lastOperation == binding.buttonEquals.text.toString()) {
            lastButton = TYPE_BUTTON_OPERATION
            return
        }

        val operationTextViewTemp = operationTextView.text.toString() +
                lastOperation + resultTextView.text + currentOperation

        performOperation(currentOperation)

        operationTextView.text = operationTextViewTemp
        lastOperation = currentOperation
        lastButton = TYPE_BUTTON_OPERATION

    }

    private fun performOperation(currentOperation: String) {

        if (operationTextView.text.toString().isEmpty()  || operationTextView.text.toString() == ERROR_RESULT) {
            operationTextView.text = "0"
        }
        if (resultTextView.text.toString().isEmpty() || resultTextView.text.toString() == ERROR_RESULT) {
            resultTextView.text = "0"
        }

//        if (lastOperation == "/" && resultTextView.text.toString().toDouble() == 0.0) {
        if (lastOperation == binding.buttonDivide.text.toString()
            && resultTextView.text.toString().toDouble() == 0.0
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                resultTextView.setTextAppearance(R.style.errorStyle)
            }

            lastOperation = binding.buttonEquals.text.toString() // = "=
            resultTextView.text = ERROR_RESULT
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

    private fun animation(view: View) {

        val animation: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_in)
        view.startAnimation(animation)

    }

}