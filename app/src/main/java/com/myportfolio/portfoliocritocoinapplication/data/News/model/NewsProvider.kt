package com.myportfolio.portfoliocritocoinapplication.data.News.model


import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class NewsProvider @Inject constructor(){
    var news : List<NewsModel> = emptyList()
}
