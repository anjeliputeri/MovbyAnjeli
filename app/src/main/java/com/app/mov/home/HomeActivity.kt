 package com.app.mov.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.app.mov.R
import com.app.mov.home.dashboard.DashboardFragment


 class HomeActivity : AppCompatActivity(){
     lateinit var menu1: ImageView
     lateinit var menu2: ImageView
     lateinit var menu3: ImageView

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
         actionBar?.hide()
         setContentView(R.layout.activity_home)

         menu1 = findViewById<ImageView>(R.id.iv_menu1)
         menu2 = findViewById<ImageView>(R.id.iv_menu2)
         menu3 = findViewById<ImageView>(R.id.iv_menu3)

         val fragmentTiket = TiketFragment()
         val fragmentSetting = SettingFragment()
         val fragmentHome = DashboardFragment()

         setFragment(fragmentHome)

         menu1.setOnClickListener {
             setFragment(fragmentHome)

             changeIcon(menu1, R.drawable.ic_home_active)
             changeIcon(menu2, R.drawable.ic_tiket)
             changeIcon(menu3, R.drawable.ic_profile)
         }

         menu2.setOnClickListener {
             setFragment(fragmentTiket)

             changeIcon(menu1, R.drawable.ic_home)
             changeIcon(menu2, R.drawable.ic_tiket_active)
             changeIcon(menu3, R.drawable.ic_profile)
         }

         menu3.setOnClickListener {
             setFragment(fragmentSetting)

             changeIcon(menu1, R.drawable.ic_home)
             changeIcon(menu2, R.drawable.ic_tiket)
             changeIcon(menu3, R.drawable.ic_profile_active)
         }

     }

     private fun setFragment(fragment: Fragment) {
         val fragmentManager = supportFragmentManager
         val fragmentTransacion = fragmentManager.beginTransaction()
         fragmentTransacion.replace(R.id.layout_frame, fragment)
         fragmentTransacion.commit()
     }

     private fun changeIcon(imageView: ImageView, int: Int) {
         imageView.setImageResource(int)
     }
 }
