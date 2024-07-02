package com.myportfolio.portfoliocritocoinapplication.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.myportfolio.portfoliocritocoinapplication.data.room.entities.CoinEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CoinDao {

    @Query("SELECT * FROM coins")
    fun getAllCoins(): Flow<List<CoinEntity>>

    @Insert
    suspend fun addCoin(item:CoinEntity)
}