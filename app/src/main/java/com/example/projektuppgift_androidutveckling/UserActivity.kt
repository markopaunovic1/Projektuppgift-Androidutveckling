package com.example.projektuppgift_androidutveckling

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var password: EditText

    lateinit var adress: EditText
    lateinit var phoneNumber: EditText

    lateinit var saveAndLoginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        auth = Firebase.auth

        name = findViewById(R.id.createPrivNameET)
        email = findViewById(R.id.createPrivEmailET)
        password = findViewById(R.id.createPrivPswET)

        adress = findViewById(R.id.createPrivAdET)
        phoneNumber = findViewById(R.id.createPrivPhoET)

        saveAndLoginButton = findViewById(R.id.saveAndLogInButton)

        saveAndLoginButton.setOnClickListener {
            createUserAccount()
           // nextActivity()
        }
    }
/*
    fun nextActivity() {
        val intent = Intent(this, resyclerView::class.java)
        startActivity(intent)
    }

 */


    fun createUserAccount() {

        val email = email.text.toString()
        val password = password.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("!!!", "Created user succeeded")
                ///Next activity()
            } else {
                Log.d("!!!", "user not created ${task.exception}")
            }
        }
    }
}