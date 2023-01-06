package com.app.mov.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.app.mov.R
import com.app.mov.model.Checkout
import com.app.mov.model.Film

class PilihBangkuActivity : AppCompatActivity() {
    lateinit var tv_kursi: TextView
    lateinit var a3: ImageView
    lateinit var a4: ImageView
    lateinit var btn_home: Button

    var statusA3: Boolean = false
    var statusA4: Boolean = false
    var total: Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku)
        tv_kursi = findViewById(R.id.Tkursi)
        a3 = findViewById(R.id.a3)
        a4 = findViewById(R.id.a4)
        btn_home = findViewById(R.id.Bbeli)

        val data = intent.getParcelableExtra<Film>("data")
        tv_kursi.text = data?.judul

        a3.setOnClickListener {
            if (statusA3) {
                a3.setImageResource(R.drawable.ic_empty)
                statusA3 = false
                total -= 1
                beliTiket(total)
            } else {
                a3.setImageResource(R.drawable.ic_selected)
                statusA3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A3", "7000")
                dataList.add(data)
            }
        }
        a4.setOnClickListener {
            if (statusA4) {
                a4.setImageResource(R.drawable.ic_empty)
                statusA4 = false
                total -= 1
                beliTiket(total)
            } else {
                a4.setImageResource(R.drawable.ic_selected)
                statusA4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A3", "7000")
                dataList.add(data)
            }
        }
        btn_home.setOnClickListener{
            var intent = Intent(this, CheckoutActivity::class.java)
                .putExtra("data", dataList)
            startActivity(intent)
        }
    }

        private fun beliTiket(total: Int) {
            if (total == 0) {
                btn_home.setText("Beli Tiket")
                btn_home.visibility = View.INVISIBLE
            } else {
                btn_home.setText("Beli Tiket (" + total + ")")
                btn_home.visibility = View.VISIBLE
            }
        }
    }
