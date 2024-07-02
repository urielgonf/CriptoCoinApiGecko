package com.myportfolio.portfoliocritocoinapplication.di.network

import com.myportfolio.portfoliocritocoinapplication.data.Coins.network.ApiService
import com.myportfolio.portfoliocritocoinapplication.di.Qualifiers.CoinRetrofit
import com.myportfolio.portfoliocritocoinapplication.util.Constants.Companion.BASE_URL_COINS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @CoinRetrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_COINS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCoinApiClient(@CoinRetrofit retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

