package com.myportfolio.portfoliocritocoinapplication.data.News.network

import android.util.Log
import com.myportfolio.portfoliocritocoinapplication.data.News.model.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsService @Inject constructor(
    private val api: ApiServiceNews
) {
    suspend fun getNews(): List<NewsModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getNewList()
            Log.d("News", "service $response")
            response.body()?.news ?: emptyList()
        }
    }
}