package com.app.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.app.mov.R
import com.app.mov.signs.signin.SignInActivity

class OnboardingThreeActivity : AppCompatActivity() {
    lateinit var Blewati3 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        Blewati3 = findViewById<Button>(R.id.BLanjutkan)

        Blewati3.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}