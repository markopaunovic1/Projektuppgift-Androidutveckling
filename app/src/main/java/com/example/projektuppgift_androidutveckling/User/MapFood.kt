package com.example.projektuppgift_androidutveckling.User

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.Restaurant

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.projektuppgift_androidutveckling.databinding.ActivityMapFoodBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


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



        createRestaurantMarker()
        showGpsLocation(GpsActivity())


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
                        document.data["restaurantAddress"].toString(),
                        document.data["restaurantName"].toString(),
                        document.data["restaurantAddress"].toString(),


                    )
                }
            }
        }
    }

    fun showGpsLocation(GpsActivity: GpsActivity) {



        db.collection("users").get().addOnSuccessListener { result ->
            for (document in result) {



                val latty = GpsActivity.lat
                val longy = GpsActivity.long
                val latNlongy = LatLng(latty, longy)
                val user = auth.currentUser

                // Zoom in on the marker   5f = zoom in, 15f = zoom out
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latNlongy, 5f))

                val markerSecond = mMap.addMarker(MarkerOptions().position(latNlongy))

                markerSecond!!.tag = User(
                    document.data["documentId"].toString(),
                    document.data["name"].toString(),
                    document.data["email"].toString(),
                    document.data["password"].toString(),
                    document.data["adress"].toString(),
                    document.data["phoneNr"].toString(),

                    )



            }
        }
    }
}