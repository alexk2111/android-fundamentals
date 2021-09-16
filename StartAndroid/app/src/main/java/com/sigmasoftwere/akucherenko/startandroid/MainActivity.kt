package com.sigmasoftwere.akucherenko.startandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = MainActivity::class.java.simpleName
    private var mCount = 0
    private lateinit var mShowCount: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(LOG_TAG, "Hello World");

        mShowCount = findViewById(R.id.tv_show_count)
        mCount = getString(R.string.count_initial_value).toInt()
    }

    fun showToast(view: android.view.View) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show()

    }   
    fun countUp(view: android.view.View) {
        mCount++
        mShowCount.text = mCount.toString()
    }

}