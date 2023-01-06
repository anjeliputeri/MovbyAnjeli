package com.app.mov

import android.content.Context
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.mov.model.Plays
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.mov.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PlaysAdapter(private var data: List<Plays>,
                   private val listener:(Plays) -> Unit)
    : RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {
    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaysAdapter.ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_plays, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: PlaysAdapter.ViewHolder, position: Int){
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val tvTittle:TextView = view.findViewById(R.id.tKursiPlays)
        private val tvImage:ImageView = view.findViewById(R.id.iPosterPlays)

        fun bindItem(data:Plays, listener: (Plays) -> Unit, context: Context) {
            tvTittle.setText(data.nama)

            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(tvImage)

            itemView.setOnClickListener{
                listener(data)
            }

        }
    }



}
