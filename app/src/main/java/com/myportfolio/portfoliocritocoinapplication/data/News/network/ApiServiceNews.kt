package com.myportfolio.portfoliocritocoinapplication.data.News.network

import com.myportfolio.portfoliocritocoinapplication.data.News.model.NewsResponse
import com.myportfolio.portfoliocritocoinapplication.util.Constants.Companion.API_KEY_NEWS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServiceNews {
    @GET("latest-news")
    suspend fun getNewList(
        @Query("category") category:String  ="business",
        @Query("apiKey")  apiKey: String  = API_KEY_NEWS
    ): Response<NewsResponse>
}
