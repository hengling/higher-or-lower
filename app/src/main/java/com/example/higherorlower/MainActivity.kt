package com.example.higherorlower

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlin.random.Random

const val RANGE_BEGIN: Int = 0
const val RANGE_END: Int = 20

class MainActivity : AppCompatActivity() {
    private var theNumberImThinking: Int = 0
    private lateinit var guessInputText: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guessInputText = findViewById(R.id.guessTextInput)
    }

    override fun onResume() {
        super.onResume()

        theNumberImThinking = generateRandomNumberBetween0And20()

        clearGuessInputText()
    }

    private fun generateRandomNumberBetween0And20(): Int {
        return Random.nextInt(RANGE_BEGIN, RANGE_END)
    }

    private fun clearGuessInputText() {
        guessInputText.editText?.setText("")
    }

    fun guess(view: View) {
        val userInput: String = guessInputText.editText?.text.toString()
        if (userInput.isEmpty() || userInput.isBlank()) {
            showInvalidInputMessage(view)
            return
        }

        val userInputConverted: Int = userInput.toInt()
        if (isNumberOutOfBounds(userInputConverted)) {
            showInvalidInputMessage(view)
        } else if (userInputConverted == theNumberImThinking) {
            val numberGuessedIntent =
                Intent(view.context, NumberGuessedActivity::class.java).apply {
                    putExtra("theNumberImThinking", theNumberImThinking.toString())
                }
            startActivity(numberGuessedIntent)
        } else {
            showTipMessage(userInputConverted, view)
        }
    }

    private fun isNumberOutOfBounds(userInput: Int): Boolean {
        return userInput !in RANGE_BEGIN..RANGE_END
    }

    private fun showInvalidInputMessage(view: View) {
        Toast.makeText(
            view.context, R.string.activity_main_invalid_input,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showTipMessage(userInput: Int, view: View) {
        val message: String = if (userInput > theNumberImThinking) {
            getString(R.string.activity_main_lower)
        } else {
            getString(R.string.activity_main_higher)
        }

        Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
    }
}