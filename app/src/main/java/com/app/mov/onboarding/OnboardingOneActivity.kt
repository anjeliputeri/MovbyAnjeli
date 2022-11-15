package com.app.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.app.mov.R
import com.app.mov.sign.SignInActivity

class OnboardingOneActivity : AppCompatActivity() {
    lateinit var buttonSkip : Button
    lateinit var buttonLanjut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)
        buttonSkip = findViewById<Button>(R.id.BLewati)
        buttonLanjut = findViewById<Button>(R.id.Blanjut)

        buttonSkip.setOnClickListener {
            finishAffinity()
            var intent = Intent (this, SignInActivity::class.java)
            startActivity(intent)
        }

        buttonLanjut.setOnClickListener {
            var intent = Intent (this, OnboardingTwoActivity::class.java)
            startActivity(intent)
        }
    }
}