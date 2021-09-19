package com.sigmasoftwere.akucherenko.startandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

private const val KEY_COUNT = "count"

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = MainActivity::class.java.simpleName
    private var count = 0
    private lateinit var showCount: TextView
    private lateinit var reset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(LOG_TAG, "Hello World")

        count = savedInstanceState?.getInt(KEY_COUNT, 0)
            ?: getString(R.string.count_initial_value).toInt()

        showCount = findViewById(R.id.tv_show_count)
        showCount.text = count.toString()

        reset = findViewById(R.id.button_reset)
        setResetColor()

//        mCount = getString(R.string.count_initial_value).toInt()
    }

    fun showToast(view: android.view.View) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show()

    }

    fun countUp(view: android.view.View) {
        count++
        showCount.text = count.toString()
        setResetColor()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_COUNT, count)
    }

    fun resetCount(view: android.view.View) {
        count = 0
        showCount.text = count.toString()
        setResetColor()
    }

    fun setResetColor() {
        if (count > 0)
            reset.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.design_default_color_primary
                )
            )
        else
            reset.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.material_on_background_disabled
                )
            )
    }
}