package com.example.projektuppgift_androidutveckling.User

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.Restaurant
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class PlacesInfoAdapter(val context: Context) : GoogleMap.InfoWindowAdapter {

    val layoutInflaterSecond = LayoutInflater.from(context)


    override fun getInfoContents(p0: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View? {
        val infoWindow = layoutInflaterSecond.inflate(R.layout.activity_map_info_window, null)

//        val locationImageView = infoWindow.findViewById<ImageView>(R.id.foodImageView)
        val locationTextView = infoWindow.findViewById<TextView>(R.id.restaurangTextView)
        val factTextView = infoWindow.findViewById<TextView>(R.id.restaurangTextView2)
        val person = infoWindow.findViewById<TextView>(R.id.personTextView)
        val personEmail = infoWindow.findViewById<TextView>(R.id.personEmailTextView)




        val place = marker.tag as? Restaurant

        locationTextView.text = place?.restaurantName
        factTextView.text = place?.restaurantAddress


        val userMark = marker.tag as? User

        person.text = userMark?.name
        personEmail.text = userMark?.email





//        if (place != null) {
//            place.image?.let { locationImageView.setImageResource(it) }
//        }




        return infoWindow


    }





}