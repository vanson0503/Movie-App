package com.example.moviesapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.R
import com.example.moviesapp.SliderItem

class SliderAdapters(private val sliderItems: List<SliderItem>) :
    RecyclerView.Adapter<SliderAdapters.SliderViewHolder>() {

    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageSlide: ImageView = itemView.findViewById(R.id.imageSlide)
        val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(60))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item_container, parent, false)
        return SliderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val currentItem = sliderItems[position]
        holder.apply {
            Glide.with(itemView)
                .load(currentItem.image)
                .apply(requestOptions)
                .into(imageSlide)
        }
    }
}
