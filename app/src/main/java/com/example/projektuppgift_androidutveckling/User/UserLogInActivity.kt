package com.example.projektuppgift_androidutveckling.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projektuppgift_androidutveckling.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserLogInActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    lateinit var email: EditText
    lateinit var password: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_log_in)

        auth = Firebase.auth
       // auth.createUserWithEmailAndPassword("lillo@outlook.com","123456") uncomment to create a user.
        email = findViewById(R.id.privMailLogET)
        password = findViewById(R.id.privPswLogET)



        val privPswLogET = findViewById<Button>(R.id.privLogInButton)
        privPswLogET.setOnClickListener {
            privateSignIn()
        }

        if (auth.currentUser != null) {
            gotToAddActivity()
        }


    }

    fun gotToAddActivity() {
        val intent = Intent(this, LandingPagePrivateActivity::class.java)
        startActivity(intent)
    }

    fun privateSignIn() {
        val email = email.text.toString()
        val password = password.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Fel email eller lösenord!", Toast.LENGTH_LONG).show()
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                Toast.makeText(this, "välkommen!", Toast.LENGTH_LONG).show()
                intent = Intent(this, LandingPagePrivateActivity::class.java)//change to correct activity.
                startActivity(intent)

            }.addOnFailureListener{
                Toast.makeText(this, "Fel email eller lösenord!", Toast.LENGTH_LONG).show()
            }
        }
    }
}