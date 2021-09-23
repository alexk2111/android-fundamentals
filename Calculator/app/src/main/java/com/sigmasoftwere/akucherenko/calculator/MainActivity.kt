package com.sigmasoftwere.akucherenko.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.sigmasoftwere.akucherenko.calculator.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val resultCalculate: Double = 0.0
    private lateinit var resultTextView: TextView
    private lateinit var operationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        resultTextView = binding.resultCalculate
        operationTextView = binding.operationCalculate

        resultTextView.text = "0"
        operationTextView.text = "0"
    }

}