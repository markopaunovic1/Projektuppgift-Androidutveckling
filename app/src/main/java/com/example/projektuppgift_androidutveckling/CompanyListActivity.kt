package com.example.projektuppgift_androidutveckling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CompanyListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_list)

        val addItemButton = findViewById<FloatingActionButton>(R.id.addItemButton)
        addItemButton.setOnClickListener{
            val intent = Intent(this, AddFood:: class.java)
            startActivity(intent)
        }

    }
}