package com.example.projektuppgift_androidutveckling

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantRecyclerViewAdapter (val context : Context, val dishes : List<Dish>)
                : RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder>() {
    val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.company_restaurant_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishes[position]
        holder.dishName.text = dish.dishName
        holder.listItemPosition = position
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val dishName = itemView.findViewById<TextView>(R.id.dishNameTV)
        var listItemPosition = 0
    }

}