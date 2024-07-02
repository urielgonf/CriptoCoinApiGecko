package com.myportfolio.portfoliocritocoinapplication.domain.GetCoins

import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinModel
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.FavouritesModel
import com.myportfolio.portfoliocritocoinapplication.data.repository.CoinRepositories.CoinRepository
import com.myportfolio.portfoliocritocoinapplication.data.repository.CoinRepositories.FavoritesRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllCoinsUseCase @Inject constructor(
    private val repository: CoinRepository,

){
////////////////////////////////////////////////////llamada al repositorio
    suspend operator  fun invoke(): List <CoinModel> = repository.getAllCoins()

}