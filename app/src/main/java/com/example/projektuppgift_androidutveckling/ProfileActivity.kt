package com.example.projektuppgift_androidutveckling

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class ProfileActivity : AppCompatActivity() {



    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val backButton = findViewById<ImageButton>(R.id.backButtonProfile)

        backButton.setOnClickListener {
            val intent = Intent(this, LandingPagePrivateActivity::class.java)
            startActivity(intent)
        }


    }
}