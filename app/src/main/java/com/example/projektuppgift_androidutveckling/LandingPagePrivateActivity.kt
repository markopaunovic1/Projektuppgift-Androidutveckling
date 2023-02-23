package com.example.projektuppgift_androidutveckling


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LandingPagePrivateActivity : AppCompatActivity() {


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

        val landingPageMapButton = findViewById<ImageButton>(R.id.LandingPageMapsButton)
        landingPageMapButton.setOnClickListener {
            val intent = Intent(this,MapFood::class.java)
            startActivity(intent)
        }
        val landPageCampaignButton = findViewById<ImageButton>(R.id.LandPageCampaignButton)
        landPageCampaignButton.setOnClickListener {
            val intent = Intent(this,GpsActivity::class.java)
            startActivity(intent)
        }


        auth = Firebase.auth

        navOptions()


    }

    private fun navOptions(){

        val navigation = findViewById<NavigationView>(R.id.nav_view)

        navigation.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_orders -> {

                    true
                }
                R.id.nav_service -> {

                    val intent = Intent(this, SupportWindow::class.java)
                    startActivity(intent)

                    true
                }
                R.id.nav_settings -> {

                    true
                }
                R.id.nav_share -> {

                    true
                }
                R.id.nav_info -> {

                    true
                }
                R.id.nav_logout -> {
                    Log.d("auth.signout", "logout")
                    auth.signOut()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }

    }

}

