package com.example.projektuppgift_androidutveckling.Company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class OffersRecyclerView : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    lateinit var db : FirebaseFirestore
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers_recycler_view)

        db = Firebase.firestore
        auth = Firebase.auth
        recyclerView = findViewById(R.id.offerRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@OffersRecyclerView)
        val currentUser = auth.currentUser
        if(currentUser != null){
            val docRef = db.collection("Owners").document(currentUser.uid)
                .collection("Offers")
            docRef.get().addOnSuccessListener {documentSnapShot->
                OfferDataManager.offerList.clear()
                for(document in documentSnapShot.documents){
                    val offer = document.toObject<Offer>()
                    if(offer != null){
                        OfferDataManager.offerList.add(offer)
                    }
                }
                recyclerView.adapter = CompanyOfferAdapter(this@OffersRecyclerView, OfferDataManager.offerList)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }

        val addOffer = findViewById<FloatingActionButton>(R.id.offerAddButton)
        addOffer.setOnClickListener{
            intent = Intent(this, AddOffer :: class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

data class Offer(@DocumentId var documentId: String? = null, val offerName : String? = null,
                    val offeerInfo : String? = null, val offerPrice : String? = null)

object OfferDataManager{
    val offerList = mutableListOf<Offer>()
}