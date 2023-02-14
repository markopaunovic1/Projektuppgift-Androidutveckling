package com.example.projektuppgift_androidutveckling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class PrivateListActivity : AppCompatActivity() {
    lateinit var adapter: RecyclerAdapterRestaurante
    lateinit var recycler : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.private_activity_list)
        recycler = findViewById(R.id.RestauranteRecyclerView)

        adapter = RecyclerAdapterRestaurante(this, arrayListOf())
        recycler.adapter = adapter;
        recycler.layoutManager = LinearLayoutManager(this)

        var db = Firebase.firestore
        var collection = db.collection("Restaurants")

        collection.get().addOnSuccessListener { documentSnapShot ->
            val restaurants = arrayListOf<Restaurant>()
            for (document in documentSnapShot.documents){
                val restaurant = document.toObject<Restaurant>()

                if (restaurant != null) {
                    restaurants.add(restaurant)
                }

            }
            adapter.setList(restaurants)
            Log.d("!!!!","GOT "+restaurants.size + " restaurants!")
        }.addOnFailureListener{
            Log.d("!!!!", "fel")
        }

    }
}