package com.mayonnaise.a7minutesworkoutapp

import android.graphics.drawable.Drawable

object exercises {
    var currentExercise = arrayListOf<String>("Get ready for: Jumping-Jacks:", "Jumping-Jacks", "Squats", "Pushups", "Crunches", "Plank", "Pushups", "Jumping-Jacks")
    var currentExerciseLength = arrayListOf<Long>(11000, 46000, 46000, 46000, 46000, 46000, 46000, 46000) // set all to 46000 later (except the first one)
    var currentExerciseDrawable = arrayListOf(R.drawable.logo_white, R.drawable.jumpingjacks, R.drawable.squats, R.drawable.pushups, R.drawable.crunches, R.drawable.plank, R.drawable.pushups, R.drawable.jumpingjacks)
    var currentExerciseInstruction = arrayListOf<String>()
}