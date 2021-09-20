package com.sigmasoftwere.akucherenko.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.sigmasoftwere.akucherenko.twoactivities.databinding.ActivitySecondBinding

const val EXTRA_REPLY = "com.sigmasoftwere.akucherenko.twoactivities.extra.REPLY"

class SecondActivity : AppCompatActivity() {

    private lateinit var messageText: TextView
    private lateinit var answer: EditText
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        answer = binding.answerMessageEditText
        messageText = binding.receivedMessageTextView
        messageText.text = message
    }

    fun launchMainActivity(view: android.view.View) {
        val reply = answer.text.toString()
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_REPLY, reply)
        setResult(RESULT_OK,replyIntent)
        finish()
    }
}