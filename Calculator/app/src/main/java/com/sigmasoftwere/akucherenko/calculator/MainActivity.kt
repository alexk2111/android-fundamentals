package com.sigmasoftwere.akucherenko.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.sigmasoftwere.akucherenko.calculator.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val resultCalculate: Double = 0.0
    private lateinit var resultTextView: TextView
    private lateinit var operationTextView: TextView
    private var lastOperation: String = ""
    private var operation = 0

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
        operation = 0
    }

    fun onNumberClick(view: android.view.View) {
        val button: Button = view as Button
        resultTextView.text = resultTextView.text.toString() + button.text.toString()
    }

    fun onDotClick(view: android.view.View) {

//        Toast.makeText(this,
//            resultTextView.text.toString().lastIndexOf(".").toString(),
//            Toast.LENGTH_SHORT ).show()
        if (resultTextView.text.toString().lastIndexOf(".") == -1) {
            resultTextView.text = resultTextView.text.toString() + "."
        }
    }
}