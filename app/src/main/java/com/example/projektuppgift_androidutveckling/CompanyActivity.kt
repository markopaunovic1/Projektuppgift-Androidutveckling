package com.example.projektuppgift_androidutveckling

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CompanyActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var companyNameET : EditText
    lateinit var companyAddressET : EditText
    lateinit var emailET : EditText
    lateinit var organisationNumberET : EditText
    lateinit var mobileNumberET : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)

        auth = Firebase.auth

        companyNameET = findViewById(R.id.createCompNameET)
        companyAddressET = findViewById(R.id.createCompAdET)
        emailET = findViewById(R.id.createCompEmET)
        organisationNumberET = findViewById(R.id.createCompOrgET)
        mobileNumberET = findViewById(R.id.createCompPhET)

        val signUpBtn = findViewById<Button>(R.id.saveAndLogInButton2)
        signUpBtn.setOnClickListener{
            signUp()
            nextActivity()
        }
    }
    fun nextActivity() {
        val intent = Intent(this, AddFood::class.java)
        startActivity(intent)
    }

    //Sign Up function for restaurant owner.
    //If the email or password is empty or wrong, the restaurant will not be able to create an
    //account and the app will show a short message on the screen about the error.
    fun signUp(){
        val email = emailET.text.toString()
        val organisationNumber = organisationNumberET.text.toString()

        if(email.isEmpty() || organisationNumber.isEmpty()){
            Toast.makeText(this, "Email or password can't be empty", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, organisationNumber)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Unable to create account!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}