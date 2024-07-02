package com.myportfolio.portfoliocritocoinapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myportfolio.portfoliocritocoinapplication.data.News.model.NewsModel
import com.myportfolio.portfoliocritocoinapplication.domain.GetNews.GetAllNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(

    private val getAllNewsUseCase: GetAllNewsUseCase


):ViewModel() {

    private val _news = MutableLiveData<List<NewsModel>?>()
    val news: MutableLiveData<List<NewsModel>?> get() = _news

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading
    ////////////////////////////////////////
    fun getAllNews(){
        viewModelScope.launch {
            try {
                val result = getAllNewsUseCase()
                _news.value = result
                Log.d("News","$result")
            } catch (e: Exception) {
                Log.e("News", "Error fetching news", e)
            }
        }///viewmodelscope
    }//////////get all news
}