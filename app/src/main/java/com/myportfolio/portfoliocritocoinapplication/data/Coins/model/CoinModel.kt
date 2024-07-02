package com.myportfolio.portfoliocritocoinapplication.data.Coins.model

import com.google.gson.annotations.SerializedName


data class CoinModel(
    @SerializedName("id")     val id: String,
    @SerializedName("symbol")  val symbol: String,
    @SerializedName("name")    val name : String
)


