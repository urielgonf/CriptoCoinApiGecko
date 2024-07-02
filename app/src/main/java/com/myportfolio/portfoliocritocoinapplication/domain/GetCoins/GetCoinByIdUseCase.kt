package com.myportfolio.portfoliocritocoinapplication.domain.GetCoins

import android.util.Log
import com.myportfolio.portfoliocritocoinapplication.data.repository.CoinRepositories.CoinRepository
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinDetailModel
import javax.inject.Inject

class GetCoinByIdUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(id: String): CoinDetailModel? {
        Log.d("CoinWorker", "Invoking use case with id: $id")
        return repository.getCoinById(id)
    }
}


