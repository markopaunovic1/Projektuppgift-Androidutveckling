package com.example.projektuppgift_androidutveckling.User

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projektuppgift_androidutveckling.LandingPagePrivateActivity
import com.example.projektuppgift_androidutveckling.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = Firebase.auth

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


    }

    private fun displayInformationFromUser() {

        val db = Firebase.firestore
        val currentUser = auth.currentUser

        if(currentUser != null){
            val docRef = db.collectionGroup("users")
            docRef.get()
            docRef.addSnapshotListener{ snapshot, exception ->
                if(exception != null){
                    return@addSnapshotListener
                }
                snapshot?.forEach { document ->
                    val user = document.toObject<User>()
                    profileName.text = user.name
                    profileEmail.text = user.email
                    profileAdress.text = user.adress
                    profilePhone.text = user.phoneNr
                    profilePassword.text = user.password
                }
        }
        }
    }
}


