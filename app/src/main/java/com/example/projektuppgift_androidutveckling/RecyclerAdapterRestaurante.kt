package com.example.projektuppgift_androidutveckling

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterRestaurante(val context : Context, val restaurants : MutableList<Restaurant>)
    : RecyclerView.Adapter<RecyclerAdapterRestaurante.viewHolder>(){
    val layoutInflater = LayoutInflater.from(context)



    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.nameOfRestaurantTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = layoutInflater.inflate(R.layout.private_resturant_list, parent, false)
        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return restaurants.size;
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val restaurante = restaurants[position]
        holder.nameTextView.text = restaurante.restaurantName
    }

    fun setList(list: MutableList<Restaurant>) {
        restaurants.clear()
        for (restaurant in list){
            restaurants.add(restaurant)
        }
        this.notifyDataSetChanged()
    }
}