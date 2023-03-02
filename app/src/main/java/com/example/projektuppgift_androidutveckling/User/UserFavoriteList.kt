package com.example.projektuppgift_androidutveckling.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.Restaurant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text


class UserFavoriteList : AppCompatActivity() {

    lateinit var recyclerView2: RecyclerView
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_favorite_list)

        auth = Firebase.auth

        db = Firebase.firestore
        val currentUser = auth.currentUser

        recyclerView2 = findViewById(R.id.UserFavoriteRW)
        recyclerView2.layoutManager = LinearLayoutManager(this@UserFavoriteList)

        val docRef = db.collection("users").document(currentUser!!.uid).collection("favoriteRestaurants")
        docRef.get().addOnSuccessListener { documentSnapshot ->
           favoriteRestaurantDataManager.favoriteList.clear()
            for (document in documentSnapshot) {
                val userFavorite = document.toObject<Restaurant>()
                if (userFavorite != null) {
                    favoriteRestaurantDataManager.favoriteList.add(userFavorite)
                }
            }
            recyclerView2.adapter = UserFavoriteRestaurantAdapter(this@UserFavoriteList, favoriteRestaurantDataManager.favoriteList
            )
            recyclerView2.adapter?.notifyDataSetChanged()
        }
    }
    object favoriteRestaurantDataManager {
        val favoriteList = mutableListOf<Restaurant>()
    }
}