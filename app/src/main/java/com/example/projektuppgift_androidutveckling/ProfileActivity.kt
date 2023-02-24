package com.example.projektuppgift_androidutveckling

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)



        profileName = findViewById(R.id.profileNameText)
        profileAdress = findViewById(R.id.profileAdressText)
        profileEmail = findViewById(R.id.profileEmailText)
        profilePhone = findViewById(R.id.profileNumberText)
        profilePassword = findViewById(R.id.profilePasswordText)


        val backButton = findViewById<ImageButton>(R.id.backButtonProfile)

        backButton.setOnClickListener {
            val intent = Intent(this, LandingPagePrivateActivity::class.java)
            startActivity(intent)
        }

        val saveButton = findViewById<Button>(R.id.profileSaveButton)
        saveButton.setOnClickListener {

        }

        displayInformationFromUser()



    }

    private fun displayInformationFromUser() {

        val currentUser = Firebase.auth.currentUser
        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("users").document(currentUser!!.uid)

        docRef.get()
            .addOnSuccessListener { document ->
                if(document != null && document.exists()){
                    val user = document.toObject<User>()
                    profileName.text = user?.name
                    profileEmail.text = user?.email
                    profileAdress.text = user?.adress
                    profilePhone.text = user?.phoneNr
                    profilePassword.text = user?.password
                    Log.d("!!!", "Document data ${document.data}")
                } else {
                    Log.d("!!!", "no document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("!!!", "failed", exception)
            }

        }

    }













/*
profileName.text = user.name
                        profileEmail.text = user.email
                        profileAdress.text = user.adress
                        profilePhone.text = user.phoneNr
                        profilePassword.text = user.password
 */



