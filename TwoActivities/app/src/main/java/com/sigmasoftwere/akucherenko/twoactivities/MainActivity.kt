package com.sigmasoftwere.akucherenko.twoactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.widget.EditText

const val EXTRA_MESSAGE = "com.sigmasoftwere.akucherenko.twoactivities.extra.MESSAGE"

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = MainActivity::class.java.simpleName
    private lateinit var messageEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        messageEditText = findViewById(R.id.editText_main)
    }

    fun launchSecondActivity(view: android.view.View) {
        Log.d(LOG_TAG, "Button clicked!")
        val intent = Intent(this, SecondActivity::class.java)
        val message = messageEditText.getText().toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivity(intent)
    }
}