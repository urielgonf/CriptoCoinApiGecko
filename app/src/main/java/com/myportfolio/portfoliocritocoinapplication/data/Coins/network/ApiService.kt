package com.myportfolio.portfoliocritocoinapplication.data.Coins.network

import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinDetailModel
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinModel
import com.myportfolio.portfoliocritocoinapplication.util.Constants.Companion.API_KEY_COINS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("coins/list")
    suspend fun getCoinsList(
        @Query("include_platform") includePlatform: Boolean = false,
        @Query("status") status: String = "active",
        @Header("x_cg_api_key") apiKey: String = API_KEY_COINS
    ): Response<List<CoinModel>>

    @GET("coins/{id}")
    suspend fun getCoinById(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") marketData: Boolean = true,
        @Query("community_data") communityData: Boolean = false,
        @Query("developer_data") developerData: Boolean = false,
        @Query("sparkline") sparkline: Boolean = false,
        @Query("x_cg_api_key") apiKey: String = API_KEY_COINS
    ): Response<CoinDetailModel>

}