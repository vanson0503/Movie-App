package com.example.moviesapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.Data.Genres
import com.example.moviesapp.R

class CategoriesListAdapter(val categoryList:Genres):RecyclerView.Adapter<CategoriesListAdapter.CategoriesViewHolder>() {
    class CategoriesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val category = itemView.findViewById<TextView>(R.id.txtCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.category.setText(categoryList[position].name)
    }
}