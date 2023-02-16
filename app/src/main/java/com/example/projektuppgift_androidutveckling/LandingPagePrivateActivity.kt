package com.example.projektuppgift_androidutveckling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LandingPagePrivateActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)


        auth = Firebase.auth
        val landingPageLogout = findViewById<Button>(R.id.LandingPageLogOutbutton)
        landingPageLogout.setOnClickListener{
            auth.signOut()
            finish()
        }

        val landingPageRestaurantButton = findViewById<ImageButton>(R.id.LandingPageRestaurantButton)
        landingPageRestaurantButton.setOnClickListener {
            val intent = Intent(this,PrivateListActivity::class.java)
            startActivity(intent)
        }
        val landingPageCategoryButton = findViewById<ImageButton>(R.id.LandingPageCategoryButton)
        val landingPageMapButton = findViewById<ImageButton>(R.id.LandingPageMapsButton)
        val landPageCampaignButton = findViewById<ImageButton>(R.id.LandPageCampaignButton)



    }
}