package com.example.projektuppgift_androidutveckling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.auth.User

class MainActivity : AppCompatActivity() {

    //Create Accounts
    private lateinit var CreatePrivateButton: Button
    private lateinit var CreateCompanyButton: Button

    //Login Accounts
    private lateinit var LoginPrivateButton: Button
    private lateinit var LoginCompanyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CreatePrivateButton = findViewById(R.id.createPrivButton)
        CreateCompanyButton = findViewById(R.id.createCompButton)

        LoginPrivateButton = findViewById(R.id.logPrivButton)
        LoginCompanyButton = findViewById(R.id.logCompButton)

        CreatePrivateButton.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        CreateCompanyButton.setOnClickListener {
            val intent = Intent(this, CompanyActivity::class.java)
            startActivity(intent)
        }

        LoginPrivateButton.setOnClickListener {
            val intent = Intent(this, UserLogInActivity::class.java)
            startActivity(intent)
        }

        LoginCompanyButton.setOnClickListener {
            val intent = Intent(this, CompanyLogInActivity::class.java)
            startActivity(intent)
        }



    }
}