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

    private val LAST_BUTTON_STATE_KEY = "BUTTON_STATE"
    private val LAST_OPERATION_STATE_KEY = "OPERATION_STATE"
    private val RESULT_TEXT_VIEW_STATE_KEY = "RESULT_TEXT_VIEW_STATE"
    private val OPERATION_TEXT_VIEW_STATE_KEY = "OPERATION_TEXT_VIEW_STATE"


    private lateinit var resultTextView: TextView
    private lateinit var operationTextView: TextView

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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_BUTTON_STATE_KEY, lastButton)
        outState.putString(LAST_OPERATION_STATE_KEY, lastOperation)
        outState.putString(RESULT_TEXT_VIEW_STATE_KEY, resultTextView.text.toString())
        outState.putString(OPERATION_TEXT_VIEW_STATE_KEY, operationTextView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        lastButton = savedInstanceState.getString(LAST_BUTTON_STATE_KEY, TYPE_BUTTON_OPERATION)
        lastOperation =
            savedInstanceState.getString(LAST_OPERATION_STATE_KEY, LAST_OPERATION_STATE_KEY)
        resultTextView.text = savedInstanceState.getString(RESULT_TEXT_VIEW_STATE_KEY)
        operationTextView.text = savedInstanceState.getString(OPERATION_TEXT_VIEW_STATE_KEY)

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

        if (operationTextView.text.toString()
                .isEmpty() || operationTextView.text.toString() == ERROR_RESULT
        ) {
            operationTextView.text = "0"
        }
        if (resultTextView.text.toString()
                .isEmpty() || resultTextView.text.toString() == ERROR_RESULT
        ) {
            resultTextView.text = "0"
        }

        if (lastOperation == binding.buttonDivide.text.toString()
            && resultTextView.text.toString().toDouble() == 0.0
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                resultTextView.setTextAppearance(R.style.errorStyle)
            }

            lastOperation = binding.buttonEquals.text.toString()
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