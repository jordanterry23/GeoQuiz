package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity()
{
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView
    private var currentIndex = 0

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called.")
        setContentView(R.layout.activity_main)

        // Set up true button
        trueButton = findViewById(R.id.true_button)
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        // Set up false button
        falseButton = findViewById(R.id.false_button)
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        //Set Up Previous Button
        previousButton = findViewById(R.id.previous_button)
        previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            Log.d(TAG, "Current Index: $currentIndex")
            updateQuestion()
        }

        //Set up next button
        nextButton = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        // Set Up Text View
        questionTextView = findViewById(R.id.question_text_view)
        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()

    }



    override fun onStart()
    {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume()
    {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause()
    {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop()
    {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion()
    {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean)
    {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer)
        {
            R.string.correct_toast
        }
        else
        {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

}