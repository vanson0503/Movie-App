package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviesapp.Adapter.ActorsListAdapter
import com.example.moviesapp.Adapter.CategoriesListAdapter
import com.example.moviesapp.Data.Genres
import com.example.moviesapp.Data.GenresItem
import com.example.moviesapp.Data.MovesViewModel
import com.example.moviesapp.Data.MovieDetail
import com.example.moviesapp.Data.ViewModelFactory
import com.example.moviesapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var movesViewModel: MovesViewModel
    lateinit var binding: ActivityDetailBinding
    lateinit var movieDetail:MovieDetail
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    fun init(){

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.content.visibility = View.GONE
        binding.progressBarDetail.visibility = View.VISIBLE
        movesViewModel = ViewModelProvider(this,ViewModelFactory()).get(MovesViewModel::class.java)
        val id = intent.getStringExtra("id")?.toInt()
//        Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()

        movesViewModel.getMovieById(id!!).observe(this) { its ->
            Glide.with(binding.picDetail)
                .load(its.poster)
                .into(binding.picDetail)
            binding.genreView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            var genres = Genres()
            for (i in its.genres) {
                genres.add(GenresItem(0, i))
            }
            binding.genreView.adapter = CategoriesListAdapter(genres)
            binding.txtMoveName.setText(its.title)
            binding.summaryMovie.setText(its.plot)
            binding.movieTime.setText(its.runtime)
            binding.movieStart.setText(its.imdb_rating)
            binding.moveActorsInfor.setText(its.actors)
            binding.imageRcv.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.imageRcv.adapter = ActorsListAdapter(its.images)
            binding.content.visibility = View.VISIBLE
            binding.progressBarDetail.visibility = View.GONE
        }
    }

}