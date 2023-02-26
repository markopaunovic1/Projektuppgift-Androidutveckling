package com.example.projektuppgift_androidutveckling.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.Restaurant
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class PrivateListActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.private_activity_list)
        db = Firebase.firestore
        recyclerView = findViewById(R.id.RestauranteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@PrivateListActivity)

        // Retrieve Restaurant names from data base and set them in the recycler view.
        val docRef = db.collection("Restaurants")
        docRef.get().addOnSuccessListener {documentSnapShot ->
            RestaurantInfoDataManager.restaurantList.clear()
            for (document in documentSnapShot){
                val restaurant = document.toObject<Restaurant>()
                if(restaurant != null){
                    RestaurantInfoDataManager.restaurantList.add(restaurant)
                }
            }
            recyclerView.adapter = RecyclerAdapterRestaurante(this@PrivateListActivity,
                RestaurantInfoDataManager.restaurantList
            )
        }

    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }


    object RestaurantInfoDataManager {


        val restaurantList = mutableListOf<Restaurant>()

        init {

            restaurantList.add(Restaurant(restaurantName = "Test"))
        }
    }
}