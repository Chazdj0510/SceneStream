package com.example.scenestream.adapters

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scenestream.models.*
import com.example.scenestream.*
class GenreAdapter(private val genreList: List<Genre>) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genreName: TextView = itemView.findViewById(R.id.genreName)
        val genreIcon: ImageView = itemView.findViewById(R.id.genreIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genreList[position]
        holder.genreName.text = genre.name

        Glide.with(holder.itemView.context)
            .load(genre.iconUrl)
            .placeholder(R.drawable.clapper)
            .centerCrop()
            .into(holder.genreIcon)
    }

    override fun getItemCount(): Int = genreList.size
}
