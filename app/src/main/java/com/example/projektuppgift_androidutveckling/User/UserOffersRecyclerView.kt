package com.example.projektuppgift_androidutveckling.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.Company.CompanyOfferAdapter
import com.example.projektuppgift_androidutveckling.Company.Offer
import com.example.projektuppgift_androidutveckling.Company.OfferDataManager
import com.example.projektuppgift_androidutveckling.Company.UserOffersAdapter
import com.example.projektuppgift_androidutveckling.Dish
import com.example.projektuppgift_androidutveckling.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class UserOffersRecyclerView : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_offers_recycler_view)
        db = Firebase.firestore
        recyclerView = findViewById(R.id.userOffersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@UserOffersRecyclerView)

        val position = intent.getIntExtra("Key", -1)
        val restaurantPosition = PrivateListActivity.RestaurantInfoDataManager.restaurantList[position].documentId
        if(restaurantPosition != null){
            val docRef = db.collection("Owners").document(restaurantPosition).collection("Offers")
            docRef.get().addOnSuccessListener {documentSnapShot ->
                UserOfferDataManager.userOfferList.clear()
                for(document in documentSnapShot){
                    val offer = document.toObject<Offer>()
                    if(offer != null){
                        UserOfferDataManager.userOfferList.add(offer)
                    }
                }
                recyclerView.adapter = UserOffersAdapter(this@UserOffersRecyclerView, UserOfferDataManager.userOfferList)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }
}

object UserOfferDataManager{
    val userOfferList = mutableListOf<Offer>()
}