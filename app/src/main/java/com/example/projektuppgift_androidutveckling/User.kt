package com.example.projektuppgift_androidutveckling

import com.google.firebase.firestore.DocumentId

data class User(@DocumentId var documentId : String? = null, var name : String? = null,
                var email : String? = null, var password : String? = null,
                var adress : String? = null, var phoneNr : String? = null)
