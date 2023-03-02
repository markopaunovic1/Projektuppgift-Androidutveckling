package com.example.projektuppgift_androidutveckling.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.projektuppgift_androidutveckling.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        val backButton = findViewById<ImageButton>(R.id.backButtonSettings)

        backButton.setOnClickListener {
            val intent = Intent(this, LandingPagePrivateActivity::class.java)
            startActivity(intent)
        }
    }
}