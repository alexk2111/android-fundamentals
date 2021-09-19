package com.sigmasoftwere.akucherenko.startandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.sigmasoftwere.akucherenko.startandroid.databinding.ActivityMainBinding
import timber.log.Timber

private const val KEY_COUNT = "count"

class MainActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Timber.d("Hello World")

        count = savedInstanceState?.getInt(KEY_COUNT, 0)
            ?: getString(R.string.count_initial_value).toInt()

        binding.tvShowCount.text = count.toString()

        setResetColor()

    }

    fun showToast(view: android.view.View) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show()

    }

    fun countUp(view: View) {
        count++
        binding.tvShowCount.text = count.toString()
        setResetColor()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_COUNT, count)
    }

    fun resetCount(view: android.view.View) {
        count = 0
        binding.tvShowCount.text = count.toString()
        setResetColor()
    }

    fun setResetColor() {
        if (count > 0)
            binding.buttonReset?.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.design_default_color_primary
                )
            )
        else
            binding.buttonReset?.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.material_on_background_disabled
                )
            )
    }
}