package com.example.projektuppgift_androidutveckling.User

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.Restaurant

class UserFavoriteRestaurantAdapter(val context : Context ,val favoriteRestaurants : List<Restaurant>)
    : RecyclerView.Adapter<UserFavoriteRestaurantAdapter.TheViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFavoriteRestaurantAdapter.TheViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.private_resturant_list,
            parent, false)
        return TheViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserFavoriteRestaurantAdapter.TheViewHolder, position: Int) {
        val favoriteRestaurants = favoriteRestaurants[position]
        holder.nameTextView.text = favoriteRestaurants.restaurantName
        holder.listItemPosition = position
    }

    override fun getItemCount(): Int {
        return favoriteRestaurants.size
    }

    class TheViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {

        val nameTextView = itemView.findViewById<TextView>(R.id.nameOfRestaurantTextView)
        val favoriteCheckBox = itemView.findViewById<CheckBox>(R.id.cb_Favorite)
        var listItemPosition = 0

    }
}