package com.example.projektuppgift_androidutveckling

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.projektuppgift_androidutveckling.Company.RestaurantChoice
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class CompanyActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore
    lateinit var companyNameET: EditText
    lateinit var companyAddressET: EditText
    lateinit var emailET: EditText
    lateinit var organisationNumberET: EditText
    lateinit var mobileNumberET: EditText
    lateinit var restaurantImage: ImageView
    var imageResUri: Uri? = null
    var uriResImage: String? = null
    lateinit var storageRefr: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)

        auth = Firebase.auth
        db = Firebase.firestore

        storageRefr = FirebaseStorage.getInstance().reference.child("restaurant_images")

        companyNameET = findViewById(R.id.createCompNameET)
        companyAddressET = findViewById(R.id.createCompAdET)
        emailET = findViewById(R.id.createCompEmET)
        organisationNumberET = findViewById(R.id.createCompOrgET)
        mobileNumberET = findViewById(R.id.createCompPhET)
        restaurantImage = findViewById(R.id.imageViewRes)

        val signUpBtn = findViewById<Button>(R.id.saveAndLogInButton2)
        signUpBtn.setOnClickListener {
            signUp()
        }

        restaurantImage.setOnClickListener {
            selectImage()
        }

    }

    fun selectImage() {
        resultLauncher.launch("image/*")
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageResUri = it
        restaurantImage.setImageURI(it)

    }

    fun nextActivity() {
        val intent = Intent(this, RestaurantChoice::class.java)
        startActivity(intent)
    }

    //Sign Up function for restaurant owner.
    //If the email or password is empty or wrong, the restaurant will not be able to create an
    //account and the app will show a short message on the screen about the error.
    fun signUp() {
        val email = emailET.text.toString()
        val organisationNumber = organisationNumberET.text.toString()

        if (email.isEmpty() || organisationNumber.isEmpty()) {
            Toast.makeText(this, "Email or password can't be empty", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, organisationNumber)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                    uploadRestaurantInfo()
                    nextActivity()
                } else {
                    Toast.makeText(this, "Unable to create account!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    //Function to upload the restaurant information to FireStore so we can retrieve them later when needed.
    fun uploadRestaurantInfo() {
        storageRefr = storageRefr.child(System.currentTimeMillis().toString())
        imageResUri?.let {
            storageRefr.putFile(it).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    storageRefr.downloadUrl.addOnSuccessListener { uri ->
                        val resImageUri = uri.toString()
                        uriResImage = resImageUri

                        val restaurantName = companyNameET.text.toString()
                        val restaurantAddress = companyAddressET.text.toString()
                        val restaurantEmail = emailET.text.toString()
                        val restaurantNumber = mobileNumberET.text.toString()
                        val restaurantImage = uriResImage
                        val currentUser = auth.currentUser

                        val newRestaurant = Restaurant(
                            restaurantName = restaurantName,
                            restaurantAddress = restaurantAddress,
                            restaurantEmail = restaurantEmail,
                            restaurantNumber = restaurantNumber,
                            restaurantImage = restaurantImage
                        )
                        if (currentUser != null) {
                            //I have used set instead of add in order to user the same Restaurants collection without
                            // having to create another collection
                            db.collection("Restaurants").document(currentUser.uid).set(newRestaurant)
                                .addOnSuccessListener {
                                    Log.d("!!!", "upload success!")
                                }
                                .addOnFailureListener {
                                    Log.d("!!!", "error uploading")
                                }
                    }
                }
            }

            }
        }
    }
}

    data class Restaurant(
        @DocumentId var documentId: String? = null, val restaurantName: String? = null,
        val restaurantAddress: String? = null, val restaurantEmail: String? = null,
        val restaurantNumber: String? = null, val restaurantImage: String? = null
    )
