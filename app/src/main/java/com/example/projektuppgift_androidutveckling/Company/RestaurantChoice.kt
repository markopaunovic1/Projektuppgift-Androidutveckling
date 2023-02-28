package com.example.projektuppgift_androidutveckling.Company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.projektuppgift_androidutveckling.CompanyListActivity
import com.example.projektuppgift_androidutveckling.R

class RestaurantChoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resturant_choice)

        val menu = findViewById<ImageButton>(R.id.menuImageButton)
        menu.setOnClickListener{
            gotToMenuActivity()
        }

        val offers = findViewById<ImageButton>(R.id.offersImageButton)
        offers.setOnClickListener{
            gotToOffersActivity()
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