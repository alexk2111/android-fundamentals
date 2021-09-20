package com.sigmasoftwere.akucherenko.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

const val  EXTRA_REPLY = "com.sigmasoftwere.akucherenko.twoactivities.extra.REPLY"

class SecondActivity : AppCompatActivity() {

    private lateinit var messageText: TextView
    private lateinit var reply: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        messageText = findViewById(R.id.text_message)
        messageText.text = message

        reply = findViewById(R.id.editText_second)
    }

    fun launchMainActivity(view: android.view.View) {
        val reply = reply.getText().toString()
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_REPLY, reply)
        setResult(RESULT_OK,replyIntent)
        finish()
    }
}