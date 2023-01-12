package com.app.mov.tiket

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mov.R
import com.app.mov.model.Checkout
import com.app.mov.model.Film
import com.bumptech.glide.Glide

class TiketActivity : AppCompatActivity() {
    lateinit var tv_tittle: TextView
    lateinit var tv_genre: TextView
    lateinit var tv_rate: TextView
    lateinit var iv_poster_image: ImageView
    lateinit var rc_checkout: RecyclerView
    lateinit var iv_barcode: ImageView
    lateinit var iv_close: ImageView


    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        tv_tittle = findViewById(R.id.tv_tittle)
        tv_genre = findViewById(R.id.tv_genre)
        tv_rate = findViewById(R.id.tv_rate)
        iv_poster_image = findViewById(R.id.iTiket)
        rc_checkout = findViewById(R.id.rc_checkout)
        iv_barcode = findViewById(R.id.iv_barcode)
        iv_close = findViewById(R.id.iv_close)


        var data = intent.getParcelableExtra<Film>("data")

        iv_close.setOnClickListener{
            finish()
        }

        iv_barcode.setOnClickListener{
            showDialog("Silahkan melakukan scanning pada counter tiket terdekat")
        }

        tv_tittle.text = data?.judul
        tv_genre.text = data?.genre
        tv_rate.text = data?.rating

        Glide.with(this)
            .load(data?.poster)
            .into((iv_poster_image))

        rc_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C1", ""))
        dataList.add(Checkout("C2", ""))

        rc_checkout.adapter = TiketAdapter(dataList){

        }

    }

    private fun showDialog(tittle:String){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.activity_scanqr)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val tScan = dialog.findViewById<TextView>(R.id.Tscan)
        tScan.text = tittle

        val btnClose = dialog.findViewById<Button>(R.id.bBacktiket)
        btnClose.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }
}