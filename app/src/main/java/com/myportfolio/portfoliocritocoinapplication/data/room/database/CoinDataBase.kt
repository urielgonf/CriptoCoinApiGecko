package com.myportfolio.portfoliocritocoinapplication.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myportfolio.portfoliocritocoinapplication.data.room.dao.CoinDao
import com.myportfolio.portfoliocritocoinapplication.data.room.entities.CoinEntity

@Database(entities = [CoinEntity::class], version = 1 )
abstract class CoinDataBase:RoomDatabase() {

    abstract fun coinDao():CoinDao

}