package com.example.projektuppgift_androidutveckling.User

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.CartManager
import com.example.projektuppgift_androidutveckling.Dish
import com.example.projektuppgift_androidutveckling.R
import com.squareup.picasso.Picasso

class MenuAdapter (val context : Context, val menuList : List<Dish>)
    : RecyclerView.Adapter<MenuAdapter.ViewHolder>(){
    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.dishitem, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        val dish = menuList[position]
        holder.dishPosition = position
        holder.name.text = dish.dishName
        holder.ingredients.text = dish.dishIngredients
        holder.price.text = dish.dishPrice
        Picasso.get().load(dish.dishImage).into(holder.image)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.nameTV)
        val ingredients = itemView.findViewById<TextView>(R.id.ingredientsTV)
        val price = itemView.findViewById<TextView>(R.id.priceTextView)
        val image = itemView.findViewById<ImageView>(R.id.dishIV)
        val addDishButton = itemView.findViewById<ImageButton>(R.id.AddDishButton)

        var dishPosition = 0
        init {
            addDishButton.setOnClickListener {
                CartManager.cartList.add(menuList[dishPosition])
                Toast.makeText(context, "Lade till " + menuList[dishPosition].dishName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}