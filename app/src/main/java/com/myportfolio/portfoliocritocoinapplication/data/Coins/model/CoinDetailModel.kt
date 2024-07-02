package com.myportfolio.portfoliocritocoinapplication.data.Coins.model

import com.google.gson.annotations.SerializedName

data class CoinDetailModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("hashing_algorithm") val hashingAlgorithm: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("description") val description: Description,
    @SerializedName("market_data") val marketData: MarketData,
    @SerializedName("image") val image: Image,

    ) {
    data class Description(
        @SerializedName("en") val en: String
    )

    data class Image(
        @SerializedName("large") val large: String,
        @SerializedName("thumb") val thumb: String
    )

    data class MarketData(
        @SerializedName("price_change_percentage_24h") val price_change_percentage_24h: Double,
        @SerializedName("current_price") val currentPrice: CurrentPrice
    ) {
        data class CurrentPrice(
            @SerializedName("usd") val usd: Double
        )

    }

}


