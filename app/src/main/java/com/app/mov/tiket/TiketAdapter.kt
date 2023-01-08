package com.app.mov.tiket

import android.content.Context
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.mov.model.Checkout
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.mov.R
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*

class TiketAdapter(private var data: List<Checkout>,
                   private val listener:(Checkout) -> Unit)
    : RecyclerView.Adapter<TiketAdapter.ViewHolder>() {
    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TiketAdapter.ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout_white, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: TiketAdapter.ViewHolder, position: Int){
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val tvTittle:TextView = view.findViewById(R.id.tKursiCheckout)

        fun bindItem(data:Checkout, listener: (Checkout) -> Unit, context: Context) {
            tvTittle.setText("Seat No. "+data.kursi)

            itemView.setOnClickListener{
                listener(data)
            }

        }
    }



}
