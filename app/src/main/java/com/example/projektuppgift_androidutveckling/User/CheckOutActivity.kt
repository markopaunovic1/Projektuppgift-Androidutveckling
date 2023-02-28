package com.example.projektuppgift_androidutveckling.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.projektuppgift_androidutveckling.PaymentComfirmation
import com.example.projektuppgift_androidutveckling.R

class CheckOutActivity : AppCompatActivity() {


    lateinit var totalSumTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        totalSumTextView = findViewById(R.id.PayMentTotalSumTextView)

        var payButton = findViewById<Button>(R.id.Paybutton)
        payButton.setOnClickListener {
            val intent = Intent(this, PaymentComfirmation::class.java)
            startActivity(intent)

        }


        }
    }
