package com.example.projektuppgift_androidutveckling

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserActivity : AppCompatActivity() {

    lateinit var db : FirebaseFirestore

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

        val user = User(
            name = name.text.toString(),
            email = email.text.toString(),
            password = password.text.toString(),
            adress = adress.text.toString(),
            phoneNr = phoneNumber.text.toString()
        )

        val email = email.text.toString()
        val password = password.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            return
        }
// 1.
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("!!!", "DocumentSnapshot added with ID: ${documentReference.id}")
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("!!!", "Created user succeeded")
                            nextActivity()
                        } else {
                            Log.d("!!!", "user not created ${task.exception}")
                        }
                    }
            }
            .addOnFailureListener { e ->
                Log.w("!!!", "Error adding document", e)
            }
        // 2.
    }
    fun nextActivity() {
        val intent = Intent(this, LandingPagePrivateActivity::class.java)
        startActivity(intent)
    }
}



