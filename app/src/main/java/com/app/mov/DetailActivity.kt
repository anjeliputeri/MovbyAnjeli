package com.app.mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mov.checkout.PilihBangkuActivity
import com.app.mov.model.Film
import com.app.mov.model.Plays
import com.bumptech.glide.Glide
import com.google.firebase.database.*

class DetailActivity : AppCompatActivity() {
    lateinit var tv_kursi : TextView
    lateinit var tv_genre : TextView
    lateinit var tv_desc : TextView
    lateinit var tv_rate : TextView
    lateinit var iv_poster : ImageView
    lateinit var rv_who_play : RecyclerView
    lateinit var btn_pilih_bangku : Button

    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Plays>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tv_kursi = findViewById<TextView>(R.id.Tkursi)
        tv_genre = findViewById<TextView>(R.id.Tgenre)
        tv_desc = findViewById<TextView>(R.id.Tdesc)
        tv_rate = findViewById<TextView>(R.id.Trate)
        iv_poster = findViewById<ImageView>(R.id.Iposter)
        rv_who_play = findViewById<RecyclerView>(R.id.rv_who_play)
        btn_pilih_bangku = findViewById(R.id.Bpilihbangku)

        val data = intent.getParcelableExtra<Film>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data?.judul.toString())
            .child("play")

        tv_kursi.text = data?.judul
        tv_genre.text = data?.genre
        tv_desc.text = data?.desc
        tv_rate.text = data?.rating

        Glide.with(this)
            .load(data?.poster)
            .into(iv_poster)

        rv_who_play.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        btn_pilih_bangku.setOnClickListener{
            var intent = Intent(this@DetailActivity, PilihBangkuActivity::class.java).putExtra("data", data)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+p0.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                dataList.clear()

                for(getdataSnapshot in p0.children){
                    var Film = getdataSnapshot.getValue(Plays::class.java)
                    dataList.add(Film!!)
                }

                rv_who_play.adapter = PlaysAdapter(dataList){

                }
            }
        })
    }
}