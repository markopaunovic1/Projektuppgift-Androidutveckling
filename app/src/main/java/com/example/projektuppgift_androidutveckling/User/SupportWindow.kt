package com.example.projektuppgift_androidutveckling.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.example.projektuppgift_androidutveckling.R

class SupportWindow : AppCompatActivity() {

    lateinit var emailEditText : EditText
    lateinit var messageEditText : EditText
    lateinit var sendButton : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support_window)


        emailEditText = findViewById(R.id.supportMailEditText)
        messageEditText = findViewById(R.id.supportQuestionEditTextMultiLine)
        sendButton = findViewById(R.id.sendImageButton)



        sendButton.setOnClickListener {
//            sendEmail()
        }
    }

    fun sendEmail () {

    }
}