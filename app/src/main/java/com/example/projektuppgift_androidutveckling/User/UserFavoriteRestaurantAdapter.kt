package com.example.projektuppgift_androidutveckling.User

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.Restaurant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class UserFavoriteRestaurantAdapter(val context: Context, val favoriteRestaurants: List<Restaurant>) :
    RecyclerView.Adapter<UserFavoriteRestaurantAdapter.viewHolder>() {
    val layoutInflater2 = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = layoutInflater2.inflate(R.layout.favorite_restaurant_item, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val favoriteRestaurants = favoriteRestaurants[position]
        holder.nameOfFavoriteRestaurant.text = favoriteRestaurants.restaurantName
        holder.listItemPosition = position
        holder.removeRestaurant
        Log.d("!!!", "${Picasso.get().load(favoriteRestaurants.restaurantImage).into(holder.imageOnFavoriteRestaurant)}")

        Picasso.get().load(favoriteRestaurants.restaurantImage).into(holder.imageOnFavoriteRestaurant)
    }

    override fun getItemCount(): Int {
        return favoriteRestaurants.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameOfFavoriteRestaurant = itemView.findViewById<TextView>(R.id.userFavoriteRestaurant)
        val imageOnFavoriteRestaurant = itemView.findViewById<ImageView>(R.id.userFavoriteImage)
        val removeRestaurant = itemView.findViewById<ImageButton>(R.id.ib_removeRestaurant)
        var listItemPosition = 0

        init {

            itemView.setOnClickListener {
                val intent = Intent(context, UserChoice::class.java)
                intent.putExtra("Key", listItemPosition)
                context.startActivity(intent)
            }
                removeRestaurant.setOnClickListener {
                    removeRestaurant()
                        Toast.makeText(context, "Borttagen från favoriter", Toast.LENGTH_LONG).show()
                    }
                }
        val db = Firebase.firestore
        val auth: FirebaseAuth = Firebase.auth
        val currentUser = auth.currentUser
        private fun removeRestaurant() {

            val selectedRestaurantId =
                UserFavoriteList.favoriteRestaurantDataManager.favoriteList[listItemPosition].documentId
            db.collection("users").document(currentUser!!.uid).collection("favoriteRestaurants")
                .document(selectedRestaurantId!!).delete()
                .addOnSuccessListener {
                    UserFavoriteList.favoriteRestaurantDataManager.favoriteList.removeAt(listItemPosition)
                    notifyItemRemoved(listItemPosition)
                    notifyDataSetChanged()
            }
        }
    }
}

