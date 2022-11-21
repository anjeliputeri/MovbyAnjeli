package com.app.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.app.mov.R
import com.app.mov.signs.signin.SignInActivity
import com.app.mov.util.Preferences

class OnboardingOneActivity : AppCompatActivity() {
    lateinit var buttonSkip : Button
    lateinit var buttonLanjut: Button
    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)
        buttonSkip = findViewById<Button>(R.id.BLewati)
        buttonLanjut = findViewById<Button>(R.id.Blanjut)
        preference = Preferences(this)

        if(preference.getValues("onboarding").equals("1")){
            finishAffinity()

            var intent = Intent (this, SignInActivity::class.java)
            startActivity(intent)
        }

        buttonSkip.setOnClickListener {
            preference.setValues("onboarding", "1")
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