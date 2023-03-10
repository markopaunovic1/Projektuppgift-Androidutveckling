package com.example.projektuppgift_androidutveckling.User


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.projektuppgift_androidutveckling.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LandingPagePrivateActivity : AppCompatActivity() {


    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        //val landingPageMapButton = findViewById<ImageButton>(R.id.LandingPageMapsButton)
        //val landPageCampaignButton = findViewById<ImageButton>(R.id.LandPageCampaignButton)


        val menuButton = findViewById<ImageButton>(R.id.menuButton)
        menuButton.setOnClickListener {
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
            drawerLayout.openDrawer(GravityCompat.START)
        }


        val landingPageRestaurantButton = findViewById<ImageButton>(R.id.LandingPageRestaurantButton)
        landingPageRestaurantButton.setOnClickListener {
            val intent = Intent(this, PrivateListActivity::class.java)
            startActivity(intent)
        }

        val landingPageMapButton = findViewById<ImageButton>(R.id.landingPageMapButton)
        landingPageMapButton.setOnClickListener {
            val intent = Intent(this, MapFood::class.java)
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

                R.id.nav_favorite -> {
                    val intent = Intent(this, UserFavoriteList::class.java)
                    startActivity(intent)

                    true
                }
                R.id.nav_service -> {

                    val intent = Intent(this, SupportWindow::class.java)
                    startActivity(intent)

                    true
                }
                R.id.nav_settings -> {

                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)

                    true
                }
                R.id.nav_info -> {
                    val intent = Intent(this, AboutUsActivity::class.java)
                    startActivity(intent)

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

