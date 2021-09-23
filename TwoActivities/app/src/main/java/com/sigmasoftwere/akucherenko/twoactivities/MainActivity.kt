package com.sigmasoftwere.akucherenko.twoactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import com.sigmasoftwere.akucherenko.twoactivities.databinding.ActivityMainBinding
import timber.log.Timber

const val EXTRA_MESSAGE = "com.sigmasoftwere.akucherenko.twoactivities.extra.MESSAGE"

class MainActivity : AppCompatActivity() {
    private lateinit var messageEditText: EditText
    private lateinit var binding: ActivityMainBinding
    private lateinit var replyHeadMessageTextView: TextView
    private lateinit var replyTextMessageTextView: TextView

    public val TEXT_REQUEST: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        messageEditText = binding.messageEditText
        replyHeadMessageTextView = binding.replyHeaderMessage
        replyTextMessageTextView = binding.replyTextMessage

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    fun launchSecondActivity(view: android.view.View) {
        Timber.d("Button clicked!")
        val intent = Intent(this, SecondActivity::class.java)
        val message = messageEditText.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
//        startActivity(intent)
        startActivityForResult(intent, TEXT_REQUEST)
    }
}