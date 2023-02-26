package com.example.projektuppgift_androidutveckling.User

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projektuppgift_androidutveckling.R


import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.projektuppgift_androidutveckling.databinding.ActivityMapFoodBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MapFoodActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapFoodBinding
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        db = Firebase.firestore
        auth = Firebase.auth



        val adapter = RestaurantMapInfoAdapter(this)
        mMap.setInfoWindowAdapter(adapter)



        createRestaurant()
//        createRestaurantMarkers()
        showGpsLocation(GpsActivity())

    }


    fun createRestaurant()    {

        val geocoder = Geocoder(this)
        db.collection("Restaurants").get()
            .addOnSuccessListener { result ->
                for (restaurant in result) {
                    val resturantAddress = restaurant.data["restaurantAddress"].toString()

                    val addresses: List<Address> = geocoder.getFromLocationName(resturantAddress, 1)
                    if (addresses != null && !addresses.isEmpty()) {
                        val address = addresses[0]
                        val latitude = address.latitude
                        val longitude = address.longitude
                        val latLng = LatLng(latitude, longitude)
                        val marker = mMap.addMarker(MarkerOptions().position(latLng))
                        marker!!.tag = restaurant




                    }
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }


    fun createRestaurantMarkers() {

        val geocoder = Geocoder(this)

        for (restaurant in PrivateListActivity.RestaurantInfoDataManager.restaurantList) {
            val restaurantAddresss = restaurant.restaurantAddress

            val addresses: List<Address> = geocoder.getFromLocationName(restaurantAddresss, 1)
            if (addresses != null && !addresses.isEmpty()) {
                val address = addresses[0]
                val latitude = address.latitude
                val longitude = address.longitude
                val latLng = LatLng(latitude, longitude)
                val marker = mMap.addMarker(MarkerOptions().position(latLng))
                marker!!.tag = restaurant
            }
        }

    }

    fun showGpsLocation(GpsActivity: GpsActivity) {


        val latty = GpsActivity.lat
        val longy = GpsActivity.long
        val latNlongy = LatLng(latty, longy)
        val user = auth.currentUser

//        This part zooms in on the user location
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latNlongy, 10f))



        val marker = mMap.addMarker(MarkerOptions().position(latNlongy))
        marker!!.tag = user

    }


}