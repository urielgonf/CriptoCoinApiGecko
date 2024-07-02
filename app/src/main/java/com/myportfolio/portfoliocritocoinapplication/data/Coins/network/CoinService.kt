package com.myportfolio.portfoliocritocoinapplication.data.Coins.network

import android.util.Log
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinDetailModel
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinService @Inject constructor(

        private val api: ApiService

) {
    suspend fun  getCoins():List<CoinModel>{
        return withContext(Dispatchers.IO){
            val response = api.getCoinsList()
            Log.d("Coins", "service $response")
            response.body() ?: emptyList()
        }

    }
    suspend fun getCoinById(id: String): CoinDetailModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getCoinById(id)
            response.body()
        }
    }

}