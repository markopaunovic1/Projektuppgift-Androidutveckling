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

class CompanyOfferAdapter (val context : Context, val offers : List<Offer>)
    : RecyclerView.Adapter<CompanyOfferAdapter.ViewHolder>() {
    val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.company_offers_list_item, parent, false)
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
        val offerName = itemView.findViewById<TextView>(R.id.offerNameTV)
        val offerPrice = itemView.findViewById<TextView>(R.id.offerPriceTV)
        val offerInfo = itemView.findViewById<TextView>(R.id.offerInfoTV)
        var positio = 0
        var delete = itemView.findViewById<ImageButton>(R.id.offerDeleteIB)

        init {
            delete.setOnClickListener{
                deleteOfferFromListAndDataBase()
            }
        }

        fun deleteOfferFromListAndDataBase (){
            val selectedOfferId = OfferDataManager.offerList[positio].documentId
            if(selectedOfferId != null){
                val db : FirebaseFirestore = Firebase.firestore
                val auth : FirebaseAuth = Firebase.auth
                val currentUser = auth.currentUser
                db.collection("Owners").document(currentUser!!.uid).collection("Offers")
                    .document(selectedOfferId).delete()
                    .addOnSuccessListener { Toast.makeText(context, "Delete Successful!", Toast.LENGTH_SHORT).show()
                        OfferDataManager.offerList.removeAt(positio)
                        notifyItemRemoved(positio)}
                    .addOnFailureListener { Toast.makeText(context, "Delete Unsuccessful", Toast.LENGTH_SHORT).show()}
            }
        }
    }

}