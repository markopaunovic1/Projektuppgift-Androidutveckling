package com.example.projektuppgift_androidutveckling

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class CompanyListActivity : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    lateinit var db : FirebaseFirestore
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_list)
        db = Firebase.firestore
        auth = Firebase.auth
        recyclerView = findViewById(R.id.restaurantRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@CompanyListActivity)
        val currentUser = auth.currentUser
        //to download the information from database to our list
        if(currentUser != null){
            val docRef = db.collection("Owners").document(currentUser.uid)
                .collection("Dishes")
            docRef.get().addOnSuccessListener {documentSnapShot->
                RestaurantDataManager.dishList.clear()
                for(document in documentSnapShot.documents){
                    val dish = document.toObject<Dish>()
                    if(dish != null){
                        RestaurantDataManager.dishList.add(dish)
                    }
                }
                recyclerView.adapter = RestaurantRecyclerViewAdapter(this@CompanyListActivity, RestaurantDataManager.dishList)
            }
        }

        val addItemButton = findViewById<FloatingActionButton>(R.id.addItemButton)
        addItemButton.setOnClickListener{
            val intent = Intent(this@CompanyListActivity, AddFood:: class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

data class Dish(@DocumentId var documentId: String? = null, val dishName : String? = null, val dishPrice : String? = null,
                val dishIngredients : String? = null, val dishImage : String? = null)

object RestaurantDataManager{
    val dishList = mutableListOf<Dish>()
}