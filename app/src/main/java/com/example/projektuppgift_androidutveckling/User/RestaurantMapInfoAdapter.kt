package com.example.projektuppgift_androidutveckling.User

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.Restaurant
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RestaurantMapInfoAdapter(val context: Context) : GoogleMap.InfoWindowAdapter {
    val db = Firebase.firestore

    val layoutInflater = LayoutInflater.from(context)


    override fun getInfoContents(p0: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View? {
        val infoWindow = layoutInflater.inflate(R.layout.activity_map_info_window, null)

//        var foodImageView = infoWindow.findViewById<ImageView>(R.id.foodImageView)
        val locationTextView = infoWindow.findViewById<TextView>(R.id.restaurantTextView)
        val factTextView = infoWindow.findViewById<TextView>(R.id.restaurantTextView2)





        val restaurant = marker.tag as? Restaurant

//        foodImageView = restaurant?.restaurantPicture
        locationTextView.text = restaurant?.restaurantName
        factTextView.text =  restaurant?.restaurantAddress



//        if (Restaurant != null) {
//            Restaurant.image?.let { locationImageView.setImageResource(it) }
//        }


        return infoWindow
    }
}