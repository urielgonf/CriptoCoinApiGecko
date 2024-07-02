package com.myportfolio.portfoliocritocoinapplication.domain.GetNews


import com.myportfolio.portfoliocritocoinapplication.data.News.model.NewsModel
import com.myportfolio.portfoliocritocoinapplication.data.repository.News.NewsRepository
import javax.inject.Inject

class GetAllNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    ////////////////////////////////////////////////////llamada al repositorio
    suspend operator fun invoke(): List<NewsModel> {
        return repository.getAllNews()
    }
}