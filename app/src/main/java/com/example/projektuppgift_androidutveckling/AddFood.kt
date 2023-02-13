package com.example.projektuppgift_androidutveckling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddFood : AppCompatActivity() {

    lateinit var db : FirebaseFirestore
    lateinit var auth : FirebaseAuth
    lateinit var addFoodImageView : ImageView
    lateinit var addFoodImageButton : Button
    lateinit var addFoodUploadImageButton : Button
    lateinit var addFoodNameEditText : EditText
    lateinit var addFoodPriceEditText : EditText
    lateinit var addFoodIngredientsEditText : EditText
    lateinit var addFoodSaveButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)


        db = Firebase.firestore
        auth = Firebase.auth

        addFoodImageView = findViewById(R.id.addFoodImageView)
        addFoodImageButton = findViewById(R.id.addFoodImageButton)
        addFoodUploadImageButton = findViewById(R.id.addFoodUploadImageButton)
        addFoodNameEditText = findViewById(R.id.addFoodNameEditText)
        addFoodPriceEditText = findViewById(R.id.addFoodPriceEditText)
        addFoodIngredientsEditText = findViewById(R.id.addFoodIngredientsEditText)
        addFoodSaveButton = findViewById(R.id.addFoodSaveButton)




        addFoodImageButton.setOnClickListener {
            selectImage()
        }

        addFoodUploadImageButton.setOnClickListener {
            uploadImage()
        }

        addFoodSaveButton.setOnClickListener {
            saveFood()
            finish()
        }







    }

    fun selectImage() {

    }


    fun uploadImage() {

    }


    fun saveFood() {
        val foodName = addFoodNameEditText.text.toString()
        val currentUser = auth.currentUser
        if(currentUser != null){
            val newDish = Dish(dishName = foodName)
            RestaurantDataManager.dishList.add(newDish)
            db.collection("Owners").document(currentUser.uid).collection("Dishes")
                .add(newDish)
        }
    }

}