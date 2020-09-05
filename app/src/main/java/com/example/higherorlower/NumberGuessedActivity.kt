package com.example.higherorlower

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_THE_NUMBER_IM_THINKING = "theNumberImThinking"

class NumberGuessedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guessed)

        val theNumberImThinking = intent.getStringExtra(EXTRA_THE_NUMBER_IM_THINKING)

        findViewById<TextView>(R.id.theNumberImThinkingTextView).apply {
            text = theNumberImThinking
        }
    }

    fun playAgain(view: View) {
        finish()
    }
}