package com.example.projektuppgift_androidutveckling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
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

        /*Here we need to retrieve the restaurants names from firebase and put them in restaurantList
        list in order to show the list of restaurants in the recycler view*/
        val docRef = db.collection("Restaurants")
        recyclerView.adapter = RecyclerAdapterRestaurante(this@PrivateListActivity,
            RestaurantInfoDataManager.restaurantList)
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