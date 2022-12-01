package com.app.mov.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.mov.model.Film
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.mov.R
import com.bumptech.glide.Glide

class ComingSoonAdapter(private var data: List<Film>,
                        private val listener:(Film) -> Unit)
    : RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>() {
    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComingSoonAdapter.ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_coming_soon, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: ComingSoonAdapter.ViewHolder, position: Int){
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val tvTittle:TextView = view.findViewById(R.id.tvKursi)
        private val tvGenre:TextView = view.findViewById(R.id.tvGenre)
        private val tvRate:TextView = view.findViewById(R.id.tvRate)
        private val tvImage:ImageView = view.findViewById(R.id.ivPosterImage)

        fun bindItem(data:Film, listener: (Film) -> Unit, context: Context) {
            tvTittle.setText(data.judul)
            tvGenre.setText(data.genre)
            tvRate.setText(data.rating)

            Glide.with(context)
                .load(data.poster)
                .into(tvImage)

            itemView.setOnClickListener{
                listener(data)
            }

        }
    }



}
