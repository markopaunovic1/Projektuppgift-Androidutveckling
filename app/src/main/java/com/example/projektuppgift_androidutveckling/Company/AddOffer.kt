package com.example.projektuppgift_androidutveckling.Company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.projektuppgift_androidutveckling.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AddOffer : AppCompatActivity() {
    lateinit var db : FirebaseFirestore
    lateinit var auth : FirebaseAuth
    lateinit var offerNameET : EditText
    lateinit var offerPriceET : EditText
    lateinit var offerInfoET : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_offer)
        db = FirebaseFirestore.getInstance()
        auth = Firebase.auth
        offerNameET = findViewById(R.id.offerNameET)
        offerPriceET = findViewById(R.id.offerPriceET)
        offerInfoET = findViewById(R.id.offerInfoET)
        val offerSaveButton = findViewById<Button>(R.id.saveOfferButton)
        offerSaveButton.setOnClickListener{
            uploadOffer()
            finish()
        }
    }

    fun uploadOffer(){
        val offerName = offerNameET.text.toString()
        val offerPrice = offerPriceET.text.toString()
        val offerInfo = offerInfoET.text.toString()
        val currentUser = auth.currentUser
        if(currentUser != null){
            val newOffer = Offer(offerName = offerName,
                offeerInfo = offerInfo, offerPrice = offerPrice)
            OfferDataManager.offerList.add(newOffer)
            db.collection("Owners").document(currentUser.uid).collection("Offers")
                .add(newOffer)
        }
    }
}