package com.example.wordle

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.wordle.FourLetterWordList.getRandomFourLetterWord

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get the variable
        val wordToGuess = getRandomFourLetterWord();
        val textInput = findViewById<EditText>(R.id.textInput)
        val guess1 = findViewById<TextView>(R.id.guess_1)
        val guess1Check = findViewById<TextView>(R.id.guess_1Check)
        val guess2 = findViewById<TextView>(R.id.guess_2)
        val guess2Check = findViewById<TextView>(R.id.guess2Check)
        val guess3 = findViewById<TextView>(R.id.guess_3)
        val guess3Check = findViewById<TextView>(R.id.guess3Check)
        val submitButton = findViewById<TextView>(R.id.button)
        val target = findViewById<TextView>(R.id.target)
        var numberOfGuesses = 0;
        val guessFields = listOf(guess1, guess2, guess3)
        val resulFields = listOf(guess1Check, guess2Check, guess3Check)

        // set the listener on the button
        submitButton.setOnClickListener {
            if (numberOfGuesses < 3) {
                val guess = textInput.text.toString()
                guessFields[numberOfGuesses].text = guess
                Log.d("GD", wordToGuess)
                resulFields[numberOfGuesses].text = checkGuess(guess, wordToGuess)
                numberOfGuesses++
                if (numberOfGuesses == 3) {
                    Toast.makeText(this, "You've reached the number of guesses!", Toast.LENGTH_SHORT)
                        .show()
                    target.text = wordToGuess
                }
            } else {
                Toast.makeText(this, "You've exceeded the number of guesses!", Toast.LENGTH_SHORT)
                    .show()
            }
            textInput.text.clear()
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordToGuess: String): String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }
}