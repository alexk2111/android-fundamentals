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
    private var mCount = 0
    private lateinit var mShowCount: TextView
    private lateinit var mReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(LOG_TAG, "Hello World")

        mCount = savedInstanceState?.getInt(KEY_COUNT, 0)
            ?: getString(R.string.count_initial_value).toInt()

        mShowCount = findViewById(R.id.tv_show_count)
        mShowCount.text = mCount.toString()

        mReset = findViewById(R.id.button_reset)
        setResetColor()

//        mCount = getString(R.string.count_initial_value).toInt()
    }

    fun showToast(view: android.view.View) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show()

    }

    fun countUp(view: android.view.View) {
        mCount++
        mShowCount.text = mCount.toString()
        setResetColor()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_COUNT, mCount)
    }

    fun resetCount(view: android.view.View) {
        mCount = 0
        mShowCount.text = mCount.toString()
        setResetColor()
    }

    fun setResetColor() {
        if (mCount > 0)
            mReset.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.design_default_color_primary
                )
            )
        else
            mReset.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.material_on_background_disabled
                )
            )
    }
}