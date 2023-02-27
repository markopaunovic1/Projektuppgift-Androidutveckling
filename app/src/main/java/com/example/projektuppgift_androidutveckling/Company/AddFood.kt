package com.example.projektuppgift_androidutveckling.Company

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.projektuppgift_androidutveckling.Dish
import com.example.projektuppgift_androidutveckling.R
import com.example.projektuppgift_androidutveckling.RestaurantDataManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AddFood : AppCompatActivity() {

    lateinit var db : FirebaseFirestore
    lateinit var auth : FirebaseAuth
    lateinit var addFoodImageView : ImageView
    lateinit var addFoodImageButton : Button
    lateinit var addFoodNameEditText : EditText
    lateinit var addFoodPriceEditText : EditText
    lateinit var addFoodIngredientsEditText : EditText
    lateinit var addFoodSaveButton : Button
    private var imageUri : Uri? = null
    private var uriImage : String? = null
    lateinit var storageRef : StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)


        db = FirebaseFirestore.getInstance()
        auth = Firebase.auth
        storageRef = FirebaseStorage.getInstance().reference.child("Images")

        addFoodImageView = findViewById(R.id.addFoodImageView)
        addFoodImageButton = findViewById(R.id.addFoodImageButton)
        addFoodNameEditText = findViewById(R.id.addFoodNameEditText)
        addFoodPriceEditText = findViewById(R.id.addFoodPriceEditText)
        addFoodIngredientsEditText = findViewById(R.id.addFoodIngredientsEditText)
        addFoodSaveButton = findViewById(R.id.addFoodSaveButton)




        addFoodImageButton.setOnClickListener {
            selectImage()
        }

        addFoodSaveButton.setOnClickListener {
            uploadDataAndImage()
            saveFood()
            finish()
        }
    }
    //To select image from internal storage
    fun selectImage() {
        resultLauncher.launch("image/*")
    }
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri = it
        addFoodImageView.setImageURI(it)
    }
    // To upload data to data base as well as upload image to storage
    fun uploadDataAndImage(){
        storageRef = storageRef.child(System.currentTimeMillis().toString())
        imageUri?.let {
            storageRef.putFile(it).addOnCompleteListener {task ->
                if(task.isSuccessful){
                    storageRef.downloadUrl.addOnSuccessListener {uri ->
                        val dishPicUri = uri.toString()
                        uriImage = dishPicUri


                        val foodName = addFoodNameEditText.text.toString()
                        val foodPrice = addFoodPriceEditText.text.toString()
                        val foodIngredients = addFoodIngredientsEditText.text.toString()
                        val dishImageUri = uriImage
                        val currentUser = auth.currentUser
                        if(currentUser != null){
                            val newDish = Dish(dishName = foodName, dishPrice = foodPrice, dishIngredients = foodIngredients,
                                dishImage = dishImageUri)
                            RestaurantDataManager.dishList.add(newDish)
                            db.collection("Owners").document(currentUser.uid).collection("Dishes")
                                .add(newDish)
                        }
                    }
                }
            }
        }
    }


    fun saveFood() {

    }

}