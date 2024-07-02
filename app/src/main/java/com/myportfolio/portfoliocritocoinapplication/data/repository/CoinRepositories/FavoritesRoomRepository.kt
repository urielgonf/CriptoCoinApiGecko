package com.myportfolio.portfoliocritocoinapplication.data.repository.CoinRepositories

import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.FavouritesModel
import com.myportfolio.portfoliocritocoinapplication.data.room.dao.CoinDao
import com.myportfolio.portfoliocritocoinapplication.data.room.entities.CoinEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRoomRepository @Inject constructor(
    private val coinDao: CoinDao

) {

    val coins: Flow<List<FavouritesModel>> = coinDao.getAllCoins().map { items ->  items.map {FavouritesModel(it.id,it.name,it.currentPrice) } }

    suspend fun add(coinEntity: CoinEntity){
        coinDao.addCoin(
            CoinEntity(

            coinEntity.id,
            coinEntity.name,
            coinEntity.currentPrice

        )
        )
    }

}