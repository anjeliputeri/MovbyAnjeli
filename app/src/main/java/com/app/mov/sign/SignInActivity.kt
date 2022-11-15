package com.app.mov.sign

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.mov.R
import com.google.firebase.database.FirebaseDatabase


class SignInActivity : AppCompatActivity() {
    lateinit var Blanjutin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        Blanjutin = findViewById<Button>(R.id.LanjutkanSignIn)

        Blanjutin.setOnClickListener {
// Write a message to the database
            // Write a message to the database
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("message")

            myRef.setValue("Hello, World!")
        }
    }
}