package com.myportfolio.portfoliocritocoinapplication.data.News.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("news") val news: List<NewsModel>
)

data class NewsModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("author") val author: String,
    @SerializedName("image") val image: String,
    @SerializedName("published") val published: String
)