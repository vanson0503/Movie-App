package com.example.moviesapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.Data.DataX
import com.example.moviesapp.DetailActivity
import com.example.moviesapp.R

class FilmListAdapter(val listMovies:List<DataX>):RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>() {
    class FilmViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.imgMovie)
        val title = itemView.findViewById<TextView>(R.id.txtMovieTitle)
        val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(30))
        val moveItem = itemView.findViewById<ConstraintLayout>(R.id.movieItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film,parent,false)
        return FilmViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.apply {
            Glide.with(itemView)
                .load(listMovies[position].poster)
                .apply(requestOptions)
                .into(image)
            title.setText(listMovies[position].title)
            moveItem.setOnClickListener {
                val intent = Intent(itemView.context,DetailActivity::class.java).apply {
                    putExtra("id",listMovies[position].id.toString())
//                    Toast.makeText(itemView.context, listMovies[position].id, Toast.LENGTH_SHORT).show()
                }
                itemView.context.startActivity(intent)
            }
        }
    }
}