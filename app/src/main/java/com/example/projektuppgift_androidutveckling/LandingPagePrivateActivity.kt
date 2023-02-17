package com.example.projektuppgift_androidutveckling


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.drawerlayout.widget.DrawerLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LandingPagePrivateActivity : AppCompatActivity() {


    private lateinit var drawerLayout: DrawerLayout

    private lateinit var auth : FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)


        //val landingPageLogout = findViewById<Button>(R.id.LandingPageLogOutbutton)



        val landingPageRestaurantButton = findViewById<ImageButton>(R.id.LandingPageRestaurantButton)
        landingPageRestaurantButton.setOnClickListener {
            val intent = Intent(this,PrivateListActivity::class.java)
            startActivity(intent)
        }
        val landingPageCategoryButton = findViewById<ImageButton>(R.id.LandingPageCategoryButton)
        val landingPageMapButton = findViewById<ImageButton>(R.id.LandingPageMapsButton)
        val landPageCampaignButton = findViewById<ImageButton>(R.id.LandPageCampaignButton)


        auth = Firebase.auth

    }

    private fun navDrawerOptions(){

        drawerLayout = findViewById(R.id.drawer_layout)



    }

}