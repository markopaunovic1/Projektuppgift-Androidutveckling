package com.example.projektuppgift_androidutveckling.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.projektuppgift_androidutveckling.R

class UserChoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_choice)

        val position = intent.getIntExtra("Key", -1)

        val menuButton = findViewById<ImageButton>(R.id.menuBtn)
        menuButton.setOnClickListener{
            intent = Intent(this,DishesMenuRecyclerView::class.java)
            intent.putExtra("Key", position)
            startActivity(intent)
        }

        val offersButton = findViewById<ImageButton>(R.id.offersBtn)
        offersButton.setOnClickListener{
            intent = Intent(this, UserOffersRecyclerView::class.java)
            intent.putExtra("Key", position)
            startActivity(intent)
        }
    }
}