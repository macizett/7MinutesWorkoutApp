package com.mayonnaise.a7minutesworkoutapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import java.util.*

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    var speak: Boolean = false
    var sound: Boolean = false
    var workout: Boolean = true
    var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        workout = true
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        var switchSound: Switch = findViewById(R.id.switchSound)
        var switchSpeak: Switch = findViewById(R.id.switchSpeak)
        var textViewExercise: TextView = findViewById(R.id.textViewExercise)
        var textViewCountDown: TextView = findViewById(R.id.textViewCountDown)
        var imageView2: ImageView = findViewById(R.id.imageView2)
        var progressBar: ProgressBar = findViewById(R.id.progressBar)
        var currentExercise = 0
        var firstTime: Boolean = false
        var count = exercises.currentExerciseLength[currentExercise]/1000
        textViewExercise.setText(exercises.currentExercise[0].toString())
        var exercisesCount = 0

        tts = TextToSpeech(this, this)


        fun play(){
            if(sound == true){
                val soundURI = Uri.parse(
                    "android.resource://com.mayonnaise.a7minutesworkoutapp/" + R.raw.tick)
                player = MediaPlayer.create(applicationContext, soundURI)
                player?.isLooping = false
                player?.start()
            }
        }

        fun exercise(){


            fun rest() {
                if(exercises.currentExercise.size > exercisesCount){
                    var restCountdown = 15 //set to 15 later
                    var nextExercise = exercises.currentExercise[currentExercise + 1].toString()
                    var exerciseDrawable = exercises.currentExerciseDrawable[currentExercise + 1]
                    textViewExercise.setText("Rest and get ready for: $nextExercise")
                    speakOut(textViewExercise.text.toString())
                    imageView2.setImageResource(exerciseDrawable)

                    var countDownTimerRest = object: CountDownTimer(15000,1000){    //set the millisInFuture to 15000 later
                        override fun onTick(p0: Long) {
                            restCountdown = restCountdown - 1
                            textViewCountDown.setText(restCountdown.toString())
                            if (count < 4){
                                play()
                            }
                        }

                        override fun onFinish() {
                            currentExercise = currentExercise + 1
                            textViewExercise.setText(exercises.currentExercise[currentExercise].toString())
                            speakOut(textViewExercise.text.toString())
                            exercise()
                        }
                    }
                    countDownTimerRest.start()
                }
                else {
                    if (workout == true){
                        Toast.makeText(this@ExerciseActivity, "the Workout is over", Toast.LENGTH_SHORT).show()
                        speakOut("The Workout is Over!")
                        Thread.sleep(1_000)
                        val overScreen = Intent(this@ExerciseActivity, WorkoutEndActivity::class.java)
                        startActivity(overScreen)
                        finish()
                    }
                }
            }

            var countDownTimer = object: CountDownTimer(exercises.currentExerciseLength[currentExercise],1000){
                override fun onTick(p0: Long) {
                    count = count - 1
                    textViewCountDown.setText(count.toString())
                    if (count < 4){
                        play()
                    }
                }

                override fun onFinish() {
                    exercisesCount = exercisesCount + 1
                    if (firstTime == false) {
                        currentExercise = currentExercise + 1
                        textViewExercise.setText(exercises.currentExercise[currentExercise].toString())
                        speakOut(textViewExercise.text.toString())
                        count = exercises.currentExerciseLength[currentExercise]/1000
                        firstTime = true
                        exercise()
                    }
                    else{
                        count = exercises.currentExerciseLength[currentExercise]/1000
                        rest()
                    }
                }
            }
            countDownTimer.start()
        }
        exercise()

        switchSpeak.setOnClickListener{
            if (switchSpeak.isChecked){
                speak = true
            }
            else {
                speak = false
            }

        }

        switchSound.setOnClickListener{
            if (switchSound.isChecked){
                sound = true
            }
            else {
                sound = false
            }

        }
    }

    private fun onBackPressedDialog(){
        var customDialog = Dialog(this)
        customDialog.setContentView(R.layout.backdialog)
            customDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog.setCanceledOnTouchOutside(false)
        var buttonYes: Button = customDialog.findViewById(R.id.buttonYes)
        var buttonNo: Button = customDialog.findViewById(R.id.buttonNo)

        buttonYes.setOnClickListener{
            sound = false
            speak = false
            workout = false
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }

        buttonNo.setOnClickListener{
            customDialog.dismiss()
        }

        customDialog.show()
    }

    override fun onBackPressed(){
        onBackPressedDialog()

    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
        }
    }

    private fun speakOut (text: String){

        if (speak == true){
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.stop()
        if(tts != null){
            tts?.stop()
            tts?.shutdown()
        }
    }
}