package com.example.projektuppgift_androidutveckling

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class PlacesInfoAdapter(val context: Context) : GoogleMap.InfoWindowAdapter {

    val layoutInflater = LayoutInflater.from(context)


    override fun getInfoContents(p0: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View? {
        val infoWindow = layoutInflater.inflate(R.layout.activity_map_info_window, null)

//        val locationImageView = infoWindow.findViewById<ImageView>(R.id.foodImageView)
            val locationTextView = infoWindow.findViewById<TextView>(R.id.restaurangTextView)
        val factTextView = infoWindow.findViewById<TextView>(R.id.restaurangTextView2)

        val place = marker.tag as? Restaurant

        locationTextView.text = place?.restaurantName
        factTextView.text = place?.restaurantAddress

//        if (place != null) {
//            place.image?.let { locationImageView.setImageResource(it) }
//        }




        return infoWindow


    }





}