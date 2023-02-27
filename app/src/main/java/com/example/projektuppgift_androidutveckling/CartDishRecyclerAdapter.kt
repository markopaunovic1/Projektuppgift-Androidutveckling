package com.example.projektuppgift_androidutveckling

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class CartDishRecyclerAdapter(val context : Context)
    : RecyclerView.Adapter<CartDishRecyclerAdapter.viewHolder>(){
    val layoutInflater = LayoutInflater.from(context)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = layoutInflater.inflate(R.layout.cart_item, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val dish = CartManager.cartList[position]
        holder.nameTextView.text = dish.dishName
        holder.listItemPosition = position
        holder.priceTextView.text = dish.dishPrice
    }

    override fun getItemCount(): Int {
        return CartManager.cartList.size;
    }
    fun deleteDish(position: Int){
        CartManager.cartList.removeAt(position)
        notifyDataSetChanged()
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.dishNameView)
        val priceTextView = itemView.findViewById<TextView>(R.id.dishPriceView)
        var listItemPosition = 0

        val deletDishButton = itemView.findViewById<ImageButton>(R.id.DishDeleteButton)


        init {
            deletDishButton.setOnClickListener {
                deleteDish(listItemPosition)

            }
        }
    }
}

