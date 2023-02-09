package com.example.projektuppgift_androidutveckling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CompanyLogInActivity : AppCompatActivity() {


    lateinit var auth : FirebaseAuth
    lateinit var compMailLogET: EditText
    lateinit var compOrgNrET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_log_in)

        auth = Firebase.auth
        compMailLogET = findViewById(R.id.compMailLogET)
        compOrgNrET = findViewById(R.id.compOrgNrET)


        val compLogInButton = findViewById<Button>(R.id.compLogInButton)
        compLogInButton.setOnClickListener {
            compLogIn()
        }

        if (auth.currentUser != null) {
//            gotToAddActivity()
        }



    }

//    fun gotToAddActivity() {
//        val intent = Intent(this, RecyclerActivity::class.java)  // RecyclerActivity not created yet
//        startActivity(intent)
//    }


    fun compLogIn() {

        val email = compMailLogET.text.toString()
        val password = compOrgNrET.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("!!!", "signed in")
//                    gotToAddActivity()
                } else {
                    Log.d("!!!", "user not signed in ${task.exception}")
                }

            }
    }
}