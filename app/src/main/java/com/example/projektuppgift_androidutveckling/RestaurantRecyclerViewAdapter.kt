package com.example.projektuppgift_androidutveckling

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
        holder.dishPrice.text = dish.dishPrice
        holder.dishIngredients.text = dish.dishIngredients
        holder.listItemPosition = position
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val dishName = itemView.findViewById<TextView>(R.id.dishNameTV)
        val dishPrice = itemView.findViewById<TextView>(R.id.priceTV)
        val dishIngredients = itemView.findViewById<TextView>(R.id.dishIngredientsTV)
        var listItemPosition = 0
        var delete = itemView.findViewById<ImageButton>(R.id.deleteIB)

        init {
            delete.setOnClickListener{
                deleteDishFromListAndDataBase()
            }
        }
        fun deleteDishFromListAndDataBase (){
            val selectedDishId = RestaurantDataManager.dishList[listItemPosition].documentId
            if(selectedDishId != null){
                val db : FirebaseFirestore = Firebase.firestore
                val auth : FirebaseAuth = Firebase.auth
                val currentUser = auth.currentUser
                db.collection("Owners").document(currentUser!!.uid).collection("Dishes")
                    .document(selectedDishId).delete()
                    .addOnSuccessListener { Toast.makeText(context, "Delete Successful!", Toast.LENGTH_SHORT).show()
                        RestaurantDataManager.dishList.removeAt(listItemPosition)
                        notifyItemRemoved(listItemPosition)}
                    .addOnFailureListener { Toast.makeText(context, "Delete Unsuccessful", Toast.LENGTH_SHORT).show()}
            }
        }
    }

}