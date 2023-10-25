package com.example.moviesapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R

class ActorsListAdapter(val imagesList: List<String>):RecyclerView.Adapter<ActorsListAdapter.ActorsListViewHolder>() {
    class ActorsListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.actorImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsListViewHolder {
        return ActorsListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false))
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: ActorsListViewHolder, position: Int) {
        Glide.with(holder.image)
            .load(imagesList[position])
            .into(holder.image)
    }
}