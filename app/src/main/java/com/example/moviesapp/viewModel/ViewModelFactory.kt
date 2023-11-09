package com.example.moviesapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory:ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovesViewModel::class.java)) {
            return MovesViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}