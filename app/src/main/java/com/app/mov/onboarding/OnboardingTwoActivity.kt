package com.app.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.app.mov.R

class OnboardingTwoActivity : AppCompatActivity() {
    lateinit var Blewati : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)

        Blewati = findViewById<Button>(R.id.BLanjutkan)

        Blewati.setOnClickListener {
            startActivity(Intent(this, OnboardingThreeActivity::class.java))
        }
    }
}