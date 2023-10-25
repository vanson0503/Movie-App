package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviesapp.Adapter.CategoriesListAdapter
import com.example.moviesapp.Adapter.FilmListAdapter
import com.example.moviesapp.Adapter.SliderAdapters
import com.example.moviesapp.Data.MovesViewModel
import com.example.moviesapp.Data.ViewModelFactory
import com.example.moviesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var movesViewModel: MovesViewModel
    lateinit var binding: ActivityMainBinding
    val sliderItems = mutableListOf<SliderItem>()

    private val viewPagerHandler = Handler()
    private var currentPage = 0
    private val DELAY_MS: Long = 4000 // Thời gian trễ giữa các slide

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory()
        movesViewModel = ViewModelProvider(this,factory).get(MovesViewModel::class.java)

        val sliderAdapter = SliderAdapters(sliderItems)
        binding.viewPage2.adapter = sliderAdapter
        binding.viewPage2.setPageTransformer(DepthPageTransformer())
        startViewPagerSlideShow()
        init()
        loadRecycleView()
    }

    private fun loadRecycleView() {
        movesViewModel.listBestMovies().observe(this,{
            listBestMovie->

            binding.rvBestMovies.adapter = FilmListAdapter(listBestMovie.data)
            binding.pbBestMovies.visibility = View.GONE
        })

        movesViewModel.listUpcomingMovies().observe(this,{
            listUpcomingMovies->
            binding.rvUpcomingMoves.adapter = FilmListAdapter(listUpcomingMovies.data)
            binding.pbUpcomingMoves.visibility = View.GONE
        })
        movesViewModel.getCategories().observe(this,{
            listCategories->
            binding.rvCategory.adapter = CategoriesListAdapter(listCategories)
            binding.pbCategory.visibility = View.GONE
        })
    }


    private val viewPagerRunnable: Runnable = object : Runnable {
        override fun run() {
            if (currentPage == sliderItems.size) {
                currentPage = 0
            }
            binding.viewPage2.setCurrentItem(currentPage++, true)
            viewPagerHandler.postDelayed(this, DELAY_MS)
        }
    }

    private fun startViewPagerSlideShow() {
        GlobalScope.launch(Dispatchers.Default) {
            viewPagerHandler.postDelayed(viewPagerRunnable, DELAY_MS)
        }
    }

    override fun onStop() {
        super.onStop()
        viewPagerHandler.removeCallbacks(viewPagerRunnable)
    }
    fun init(){
        sliderItems.add(SliderItem("https://thecosmiccircus.com/wp-content/uploads/2022/07/nope-banner.jpeg"))
        sliderItems.add(SliderItem("https://teaser-trailer.com/wp-content/uploads/Avengers-Infinity-War-Banner.jpg"))
        sliderItems.add(SliderItem("https://teaser-trailer.com/wp-content/uploads/Polar-new-banner.jpg"))
        binding.apply {
            rvBestMovies.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            rvCategory.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            rvUpcomingMoves.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
        }
    }

}