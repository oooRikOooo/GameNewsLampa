package com.example.gamenewslampa.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.gamenewslampa.data.ApiService
import com.example.gamenewslampa.data.NewsDataSource

class NewsViewModel(private val api: ApiService) : ViewModel() {

    val news = Pager(
        config = PagingConfig(pageSize = 5, prefetchDistance = 5),
        pagingSourceFactory = { NewsDataSource(api) }
    ).liveData.cachedIn(viewModelScope)

}