package com.example.projektuppgift_androidutveckling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PaymentComfirmation : AppCompatActivity() {

    lateinit var paymentComfirmationTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_comfirmation)

        paymentComfirmationTextView = findViewById(R.id.PaymentComfirmationTextView)
        paymentComfirmationTextView.text = CartManager.totalSum().toString() + "kr"
    }
}