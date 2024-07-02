package com.myportfolio.portfoliocritocoinapplication.di.Local

import android.content.Context
import androidx.room.Room
import com.myportfolio.portfoliocritocoinapplication.data.room.dao.CoinDao
import com.myportfolio.portfoliocritocoinapplication.data.room.database.CoinDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideCoinDao(coindDatabase: CoinDataBase): CoinDao {
        return coindDatabase.coinDao()
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CoinDataBase {
        return Room.databaseBuilder(
            context,
            CoinDataBase::class.java,
            "Coin_Database"
        ).build()
    }

}