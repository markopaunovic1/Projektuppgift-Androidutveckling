package com.example.projektuppgift_androidutveckling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class LandingPagePrivateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)


        val landingPageLogout = findViewById<Button>(R.id.LandingPageLogOutbutton)
        val landingPageRestaurantButton = findViewById<ImageButton>(R.id.LandingPageRestaurantButton)
        val landingPageCategoryButton = findViewById<ImageButton>(R.id.LandingPageCategoryButton)
        val landingPageMapButton = findViewById<ImageButton>(R.id.LandingPageMapsButton)
        val landPageCampaignButton = findViewById<ImageButton>(R.id.LandPageCampaignButton)



    }
}