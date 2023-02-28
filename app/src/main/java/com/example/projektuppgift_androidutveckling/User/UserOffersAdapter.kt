package com.example.projektuppgift_androidutveckling.Company

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projektuppgift_androidutveckling.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserOffersAdapter (val context : Context, val offers : List<Offer>)
    : RecyclerView.Adapter<UserOffersAdapter.ViewHolder>() {
    val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.user_offers_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val offer = offers[position]
        holder.offerName.text = offer.offerName
        holder.offerPrice.text = offer.offerPrice
        holder.offerInfo.text = offer.offeerInfo
        holder.positio = position
    }

    override fun getItemCount(): Int {
        return offers.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val offerName = itemView.findViewById<TextView>(R.id.userOfferNameTV)
        val offerPrice = itemView.findViewById<TextView>(R.id.userOfferPriceTV)
        val offerInfo = itemView.findViewById<TextView>(R.id.userOfferInfoTV)
        var positio = 0
    }

}