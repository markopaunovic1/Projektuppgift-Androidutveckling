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

class MapFood : AppCompatActivity(), OnMapReadyCallback {

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


        val adapter = PlacesInfoAdapter(this)
        mMap.setInfoWindowAdapter(adapter)

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

//        createMarkers()

        createPlaces()
        showGpsLocation(GpsActivity())

    }

    fun createMarkers() {
        var sthlm = LatLng(59.3, 18.0)

        var marker1 = mMap.addMarker(
            MarkerOptions()
                .position(sthlm)
                .title("Stockholm")
                .snippet("Fint här")
        )

        var marker2 = mMap.addMarker(
            MarkerOptions()
                .position(LatLng(60.0,19.0))
                .title("plats2")
                .snippet("Fint här")
        )

        var marker3 = mMap.addMarker(
            MarkerOptions()
                .position(LatLng(58.0, 17.0))
                .title("plats3")
                .snippet("Fint här")
        )




    }

    fun createPlaces()    {

        val geocoder = Geocoder(this)

        for (place in PrivateListActivity.RestaurantInfoDataManager.restaurantList) {
            val placeName = place.toString()

            val addresses: List<Address> = geocoder.getFromLocationName(placeName, 1)
            if (addresses != null && !addresses.isEmpty()) {
                val address = addresses[0]
                val latitude = address.latitude
                val longitude = address.longitude
                val latLng = LatLng(latitude, longitude)
                val marker = mMap.addMarker(MarkerOptions().position(latLng))
                marker!!.tag = place
            }
        }
    }

    fun showGpsLocation(GpsActivity: GpsActivity) {

        val latty = GpsActivity.lat
        val longy = GpsActivity.long
        val latNlongy = LatLng(latty, longy)
        val user = auth.currentUser




        val marker = mMap.addMarker(MarkerOptions().position(latNlongy))
        marker!!.tag = user

    }
}