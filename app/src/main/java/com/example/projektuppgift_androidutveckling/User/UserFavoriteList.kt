package com.example.projektuppgift_androidutveckling.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.Restaurant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class UserFavoriteList : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_favorite_list)

        db = Firebase.firestore

        recyclerView = findViewById(R.id.UserFavoriteRW)
        recyclerView.layoutManager = LinearLayoutManager(this@UserFavoriteList)

        val docRef = db.collection("Restaurants")
        docRef.get().addOnSuccessListener { documentSnapshot ->
            PrivateListActivity.RestaurantInfoDataManager.restaurantList.clear()
            for (document in documentSnapshot) {
                val userFavorite = document.toObject<Restaurant>()
                if (userFavorite != null) {
                    RestaurantInfoDataManager.restaurantList.add(userFavorite)
                }
            }
            recyclerView.adapter = UserFavoriteRestaurantAdapter(this@UserFavoriteList, RestaurantInfoDataManager.restaurantList
            )
        }
    }
    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }
    object RestaurantInfoDataManager {
        val restaurantList = mutableListOf<Restaurant>()
    }
}