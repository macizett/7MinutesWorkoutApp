package com.mayonnaise.a7minutesworkoutapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mayonnaise.a7minutesworkoutapp.databinding.ActivityBmiCalculateBinding
import com.mayonnaise.a7minutesworkoutapp.databinding.ActivityWorkoutEndBinding

class BmiCalculateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculate)

        var buttonBack: Button = findViewById(R.id.buttonBack)
        var buttonCalculate: Button = findViewById(R. id.buttonCalculate)
        var weightInput: TextInputEditText = findViewById(R.id.weightInput)
        var heightInput: TextInputEditText = findViewById(R.id.heightInput)
        var textViewBmiScore: TextView = findViewById(R.id.textViewBmiScore)
        var textViewBmiDescription: TextView = findViewById(R.id.textViewBmiDescription)

        buttonBack.setOnClickListener{
            val mainScreen = Intent(this@BmiCalculateActivity, MainActivity::class.java)
            startActivity(mainScreen)
            finish()
        }

        buttonCalculate.setOnClickListener{
            var weight = weightInput.text.toString()
            var height = heightInput.text.toString()
            var weight2 = weight.toDouble()
            var height2 = height.toDouble()
            var height3 = height2 / 100

            if (weight.isNotEmpty() && height.isNotEmpty()){
                try {
                    var heightSquare = height3 * height3
                    var bmiScore = weight2 / heightSquare
                    var bmiScoreString = bmiScore.toString()
                    var bmiScoreString2 = bmiScoreString.split(".")
                    var bmiScoreStringSplat = bmiScoreString2[0].toString()
                    textViewBmiScore.setText(bmiScoreStringSplat)
                    if (bmiScore < 19){
                        textViewBmiScore.setTextColor(Color.RED)
                        textViewBmiDescription.setTextColor(Color.RED)
                        textViewBmiDescription.setText("Underweight - it's dangerous! You should gain some weight!")
                    }
                    if (bmiScore > 19 && bmiScore < 26){
                        textViewBmiScore.setTextColor(Color.GREEN)
                        textViewBmiDescription.setTextColor(Color.GREEN)
                        textViewBmiDescription.setText("Normal weight - You have nothing to worry about!")
                    }
                    if (bmiScore > 26 && bmiScore < 30){
                        textViewBmiScore.setTextColor(Color.YELLOW)
                        textViewBmiDescription.setTextColor(Color.YELLOW)
                        textViewBmiDescription.setText("Overweight - Maybe it's time to think about a bit of exercise?")
                    }
                    if (bmiScore > 30){
                        textViewBmiScore.setTextColor(Color.RED)
                        textViewBmiDescription.setTextColor(Color.RED)
                        textViewBmiDescription.setText("Obesity - it's dangerous! You should probably lose some weight!")
                    }
                    else {
                        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT)
                    }
                }
                catch(e: java.lang.IllegalArgumentException) {
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
            }
        }



    }
}