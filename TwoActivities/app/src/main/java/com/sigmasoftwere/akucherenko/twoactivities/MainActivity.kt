package com.sigmasoftwere.akucherenko.twoactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent




class MainActivity : AppCompatActivity() {
    private val LOG_TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun launchSecondActivity(view: android.view.View) {
        Log.d(LOG_TAG, "Button clicked!")
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}