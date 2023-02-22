package com.mayonnaise.a7minutesworkoutapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imageView: ImageView = findViewById(R.id.imageView)
        var textViewAuthor: TextView = findViewById(R.id.textViewAuthor)
        var textViewQuote: TextView = findViewById(R.id.textViewQuote)
        var bmiButton: Button = findViewById(R.id.bmiButton)

        var random = Random()
        fun rand(from: Int, to: Int): Int {
            return random.nextInt(to - from) + from
        }
        var randomQuote = rand(0, 19)
        var quotes2 = quotes.quote[randomQuote]
        var author2 = quotes.author[randomQuote]


        imageView.setOnClickListener{
            val countDownScreen = Intent(this, ExerciseActivity::class.java)
            startActivity(countDownScreen)
        }


        textViewQuote.setText("„$quotes2”")
        textViewAuthor.setText(author2)

        bmiButton.setOnClickListener{
            var bmiScreen = Intent(this, BmiCalculateActivity::class.java)
            startActivity(bmiScreen)
        }

    }
    }