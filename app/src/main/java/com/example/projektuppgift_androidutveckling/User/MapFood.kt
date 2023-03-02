package com.example.projektuppgift_androidutveckling.User

import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.Restaurant

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.projektuppgift_androidutveckling.databinding.ActivityMapFoodBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.jar.Manifest


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

        // Kolla om appen har tillstånd att använda GPS
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            val swedenBounds = LatLngBounds(
                LatLng((55.3617373725), 11.0273686052),  // SW bounds
                LatLng((69.1062472602), 23.9033785336) // NE bounds
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(swedenBounds.center, 5.5f))
            mMap.setLatLngBoundsForCameraTarget(swedenBounds)

            // Appen har tillstånd att använda GPS, visa användarens position
            mMap.isMyLocationEnabled = true
            mMap.uiSettings.isMyLocationButtonEnabled = true
        } else {

            // Appen har inte tillstånd att använda GPS ännu
            // Visa en fallback-plats eller informera användaren om att appen inte kan fungera som tänkt utan tillstånd

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(59.334591, 18.063240), 5f))

        }

        createRestaurantMarker()

    }


    fun createRestaurantMarker()    {

        val geocoder = Geocoder(this)

        db.collection("Restaurants").get().addOnSuccessListener { result ->
            for (document in result) {
                val restaurantAddress = document.data["restaurantAddress"].toString()

                val addresses: List<Address> = geocoder.getFromLocationName(restaurantAddress, 1)
                if (addresses != null && !addresses.isEmpty()) {
                    val address = addresses[0]
                    val latitude = address.latitude
                    val longitude = address.longitude
                    val latLng = LatLng(latitude, longitude)
                    val marker = mMap.addMarker(MarkerOptions().position(latLng))
                    marker!!.tag = Restaurant(
                        document.data["documentId"].toString(),
                        document.data["restaurantName"].toString(),
                        document.data["restaurantAddress"].toString(),


                    )
                }
            }
        }
    }

}