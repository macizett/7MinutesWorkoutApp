package com.mayonnaise.a7minutesworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import com.mayonnaise.a7minutesworkoutapp.databinding.ActivityExerciseBinding
import com.mayonnaise.a7minutesworkoutapp.databinding.ActivityWorkoutEndBinding

class WorkoutEndActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_end)

        var buttonOk: Button = findViewById(R.id.buttonOk)

        buttonOk.setOnClickListener{
            val mainScreen = Intent(this@WorkoutEndActivity, MainActivity::class.java)
            startActivity(mainScreen)
            finish()
        }

    }
}