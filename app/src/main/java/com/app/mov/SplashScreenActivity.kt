package com.app.mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.app.mov.onboarding.OnboardingOneActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        setContentView(R.layout.activity_splash_screen)

        //durasi muncul splash screen
        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this,OnboardingOneActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}