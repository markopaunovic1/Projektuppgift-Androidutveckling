package com.example.projektuppgift_androidutveckling.User

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projektuppgift_androidutveckling.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class ProfileActivity : AppCompatActivity() {

    lateinit var profileName: TextView
    lateinit var profileAdress: TextView
    lateinit var profileEmail: TextView
    lateinit var profilePhone: TextView
    lateinit var profilePassword: TextView

    lateinit var auth: FirebaseAuth
    lateinit var db : FirebaseFirestore
    lateinit var currentUser : FirebaseUser



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = Firebase.auth
        db = Firebase.firestore
        currentUser = auth.currentUser!!

        profileName = findViewById(R.id.profileNameText)
        profileAdress = findViewById(R.id.profileAdressText)
        profileEmail = findViewById(R.id.profileEmailText)
        profilePhone = findViewById(R.id.profileNumberText)
        profilePassword = findViewById(R.id.profilePasswordText)

        displayInformationFromUser()

        val backButton = findViewById<ImageButton>(R.id.backButtonProfile)

        backButton.setOnClickListener {
            val intent = Intent(this, LandingPagePrivateActivity::class.java)
            startActivity(intent)
        }

        val saveButton = findViewById<Button>(R.id.profileSaveButton)
        saveButton.setOnClickListener {
            updateInformationFromUser()
            Toast.makeText(this, "Personlig information uppdaterad", Toast.LENGTH_LONG).show()
        }

    }

    private fun updateInformationFromUser(){

        val docRef = db.collection("users").document(currentUser.uid)

        val newName = profileName.text.toString()
        val newAdress = profileAdress.text.toString()
        val newEmail = profileEmail.text.toString()
        val newPhone = profilePhone.text.toString()
        val newPassword = profilePassword.text.toString()

        val userUpdate = hashMapOf<String, Any>(
            "name" to newName,
            "adress" to newAdress,
            "email" to newEmail,
            "phoneNr" to newPhone,
            "password" to newPassword
        )

        docRef.update(userUpdate)
            .addOnSuccessListener {
                Log.d("!!!", "user info updated")
            }
            .addOnFailureListener { e ->
                Log.w("!!!", "error", e)
            }

    }

    private fun displayInformationFromUser() {
        val docRef = db.collection("users").document(currentUser.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                Log.d("!!!", "document data: ${document.data}")
                profileName.text = document.toObject<User>()?.name
                profileEmail.text = document.toObject<User>()?.email
                profileAdress.text = document.toObject<User>()?.adress
                profilePhone.text = document.toObject<User>()?.phoneNr
                profilePassword.text = document.toObject<User>()?.password
                }
    }
}





