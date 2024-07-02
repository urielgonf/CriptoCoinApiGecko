package com.myportfolio.portfoliocritocoinapplication.data.Coins.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinProvider @Inject constructor(){
    var coins : List<CoinModel> = emptyList()
    var coin: CoinDetailModel? = null
}
