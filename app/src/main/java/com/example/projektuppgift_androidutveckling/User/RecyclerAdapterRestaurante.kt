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

        holder.favoriteCheckBox.setOnCheckedChangeListener { checkBox , isChecked ->
             if (isChecked) {
                 Toast.makeText(context, "Added to favorite", Toast.LENGTH_LONG).show()
             } else {
                 Toast.makeText(context, "Removed from favorite", Toast.LENGTH_LONG).show()
             }
        }

    }

    override fun getItemCount(): Int {
        return restaurants.size;
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.nameOfRestaurantTextView)
        val favoriteCheckBox = itemView.findViewById<CheckBox>(R.id.cb_Favorite)
        var listItemPosition = 0

        init {
            itemView.setOnClickListener{
                val intent = Intent (context, UserChoice::class.java)
                intent.putExtra("Key", listItemPosition)
                context.startActivity(intent)
            }
        }
    }
}