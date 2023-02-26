package com.example.projektuppgift_androidutveckling.User

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.Company.RestaurantRecyclerViewAdapter
import com.example.projektuppgift_androidutveckling.Dish
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.RestaurantDataManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class DishesMenuRecyclerView : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes_menu_recycler_view)

        db = Firebase.firestore
        auth = Firebase.auth

        recyclerView = findViewById(R.id.DishesMenuRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@DishesMenuRecyclerView)

        val position = intent.getIntExtra("Key", -1)
        val restaurantPosition = PrivateListActivity.RestaurantInfoDataManager.restaurantList[position].documentId
        if(restaurantPosition != null){
            val docRef = db.collection("Owners").document(restaurantPosition).collection("Dishes")
            docRef.get().addOnSuccessListener {documentSnapShot ->
                MenuDataManager.menuList.clear()
                for(document in documentSnapShot){
                    val dish = document.toObject<Dish>()
                    if(dish != null){
                        MenuDataManager.menuList.add(dish)
                    }
                }
                recyclerView.adapter = MenuAdapter(this@DishesMenuRecyclerView, MenuDataManager.menuList)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }
}

object MenuDataManager{
    val menuList = mutableListOf<Dish>()
}