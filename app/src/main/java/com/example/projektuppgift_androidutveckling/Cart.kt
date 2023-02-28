package com.example.projektuppgift_androidutveckling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.User.CheckOutActivity

class Cart : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        recyclerView = findViewById(R.id.VarukorgRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CartDishRecyclerAdapter(this)

        val goToPaymentButton = findViewById<Button>(R.id.goToPaymentButton)
        goToPaymentButton.setOnClickListener {
            val intent = Intent(this, CheckOutActivity::class.java)
            startActivity(intent)

        }
    }
}