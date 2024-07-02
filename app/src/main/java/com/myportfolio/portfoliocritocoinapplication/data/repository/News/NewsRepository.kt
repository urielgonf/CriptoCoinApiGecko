package com.myportfolio.portfoliocritocoinapplication.data.repository.News


import com.myportfolio.portfoliocritocoinapplication.data.News.model.NewsModel
import com.myportfolio.portfoliocritocoinapplication.data.News.model.NewsProvider
import com.myportfolio.portfoliocritocoinapplication.data.News.network.NewsService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsService,
    private val newsProvider: NewsProvider
) {
    suspend fun getAllNews(): List<NewsModel> {
        // Verificar si el caché ya tiene los datos y si sigue siendo válido

            val response = api.getNews()
            newsProvider.news= response
        return response
        }
}