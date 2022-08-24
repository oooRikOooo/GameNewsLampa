package com.example.gamenewslampa.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamenewslampa.data.RetrofitClient

class NewsViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            val api = RetrofitClient.apiInterface
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(api) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
