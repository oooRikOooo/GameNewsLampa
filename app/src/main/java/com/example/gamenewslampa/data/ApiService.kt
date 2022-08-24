package com.example.gamenewslampa.data

import com.example.gamenewslampa.data.models.News
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun getNews(
        @Query("page") page: Int
    ): News
}