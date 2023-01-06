package com.app.mov.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.app.mov.R
import com.app.mov.home.HomeActivity

class CheckoutSuccessActivity : AppCompatActivity() {
    lateinit var btn_home : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)
        btn_home = findViewById(R.id.Bhome)

        btn_home.setOnClickListener {
            finishAffinity()

            var intent = Intent (this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}