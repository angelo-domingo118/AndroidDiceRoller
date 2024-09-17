package com.example.dicerollerapp

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rollButton: ImageButton = findViewById(R.id.rollButton)
        val diceImage: ImageView = findViewById(R.id.diceImage)
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
        val diceRollAnimation = AnimationUtils.loadAnimation(this, R.anim.dice_roll)

        val rollDiceAndAnimate = {
            rollButton.startAnimation(rotateAnimation)
            diceImage.startAnimation(diceRollAnimation)
            val result = rollDice()
            diceImage.setImageResource(result)
        }

        rollButton.setOnClickListener { rollDiceAndAnimate() }
        diceImage.setOnClickListener { rollDiceAndAnimate() }
    }

    private fun rollDice(): Int {
        val randomNumber = Random.nextInt(6) + 1
        return when (randomNumber) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}