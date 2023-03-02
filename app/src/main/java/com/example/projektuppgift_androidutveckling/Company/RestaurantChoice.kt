package com.example.projektuppgift_androidutveckling.Company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.projektuppgift_androidutveckling.CompanyListActivity
import com.example.projektuppgift_androidutveckling.MainActivity
import com.example.projektuppgift_androidutveckling.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RestaurantChoice : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resturant_choice)

        auth = Firebase.auth

        val menu = findViewById<ImageButton>(R.id.menuImageButton)
        menu.setOnClickListener{
            gotToMenuActivity()
        }

        val offers = findViewById<ImageButton>(R.id.offersImageButton)
        offers.setOnClickListener{
            gotToOffersActivity()
        }

        val logOut = findViewById<Button>(R.id.logOutButtonRes)
        logOut.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            auth.signOut()
        }
    }

    fun gotToMenuActivity() {
        val intent = Intent(this, CompanyListActivity::class.java)
        startActivity(intent)
    }

    fun gotToOffersActivity() {
        val intent = Intent(this, OffersRecyclerView::class.java)
        startActivity(intent)
    }
}