package com.example.projektuppgift_androidutveckling.User

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.projektuppgift_androidutveckling.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore

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
        db = Firebase.firestore

        name = findViewById(R.id.createPrivNameET)
        email = findViewById(R.id.createPrivEmailET)
        password = findViewById(R.id.createPrivPswET)

        adress = findViewById(R.id.createPrivAdET)
        phoneNumber = findViewById(R.id.createPrivPhoET)

        saveAndLoginButton = findViewById(R.id.saveAndLogInButton)

        saveAndLoginButton.setOnClickListener {
            createUserAccountWithInformation()
        }
    }

    fun createUserAccountWithInformation() {

        val email = email.text.toString()
        val password = password.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("!!!", "Created user succeeded")
                    uploadPrivateUser()
                    nextActivity()
                } else {
                    Log.d("!!!", "user not created ${task.exception}")
                }
            }
    }

    fun uploadPrivateUser(){

        val privateName = name.text.toString()
        val privateEmail = email.text.toString()
        val privateAdress = adress.text.toString()
        val privatePhone = phoneNumber.text.toString()
        val privatePassword = password.text.toString()
        val currentUser = auth.currentUser

        val user = User(
            name = privateName,
            email = privateEmail,
            password = privatePassword,
            adress = privateAdress,
            phoneNr = privatePhone
        )

        if(currentUser != null){
            db.collection("users").document(currentUser.uid)
                .set(user)
        }
    }
    fun nextActivity() {
        val intent = Intent(this, LandingPagePrivateActivity::class.java)
        startActivity(intent)
    }
}





