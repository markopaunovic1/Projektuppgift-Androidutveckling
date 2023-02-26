package com.example.projektuppgift_androidutveckling.User

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.Restaurant

class RecyclerAdapterRestaurante(val context : Context, val restaurants : List<Restaurant>)
    : RecyclerView.Adapter<RecyclerAdapterRestaurante.viewHolder>(){
    val layoutInflater = LayoutInflater.from(context)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = layoutInflater.inflate(R.layout.private_resturant_list, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val restaurante = restaurants[position]
        holder.nameTextView.text = restaurante.restaurantName
        holder.listItemPosition = position
    }

    override fun getItemCount(): Int {
        return restaurants.size;
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.nameOfRestaurantTextView)
        var listItemPosition = 0

        init {
            itemView.setOnClickListener{
                val intent = Intent (context, DishesMenuRecyclerView::class.java)
                intent.putExtra("Key", listItemPosition)
                context.startActivity(intent)
            }
        }
    }
}