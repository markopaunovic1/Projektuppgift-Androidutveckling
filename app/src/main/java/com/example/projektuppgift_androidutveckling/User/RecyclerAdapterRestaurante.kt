package com.example.projektuppgift_androidutveckling.User

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.Restaurant
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecyclerAdapterRestaurante(val context: Context, val restaurants: List<Restaurant>) :
    RecyclerView.Adapter<RecyclerAdapterRestaurante.viewHolder>() {
    val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = layoutInflater.inflate(R.layout.private_resturant_list, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val restaurante = restaurants[position]
        holder.nameTextView.text = restaurante.restaurantName
        holder.listItemPosition = position
        holder.restaurantFavoriteCheckBox.isClickable
    }

    override fun getItemCount(): Int {
        return restaurants.size;
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.nameOfRestaurantTextView)
        val restaurantFavoriteCheckBox = itemView.findViewById<CheckBox>(R.id.cb_Favorite)
        var listItemPosition = 0

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, DishesMenuRecyclerView::class.java)
                intent.putExtra("Key", listItemPosition)
                context.startActivity(intent)
            }
            restaurantFavoriteCheckBox.setOnCheckedChangeListener { checkBox, isChecked ->
                if (isChecked) {
                    addRestaurant()
                    Toast.makeText(context, "Tillagd i dina favoriter", Toast.LENGTH_LONG).show()
                }
            }
        }

        val db = Firebase.firestore
        val currentUser = Firebase.auth
        private fun addRestaurant() {
            if (currentUser != null) {
                val db = Firebase.firestore
                val currentUser = Firebase.auth

                val favoriteRestaurant = Restaurant(restaurantName = nameTextView.text.toString())
                db.collection("users").document(currentUser.uid!!).collection("favoriteRestaurants")
                    .add(favoriteRestaurant)
            }
        }
    }
}