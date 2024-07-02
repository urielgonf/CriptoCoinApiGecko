package com.myportfolio.portfoliocritocoinapplication.di.network

import com.myportfolio.portfoliocritocoinapplication.data.News.network.ApiServiceNews
import com.myportfolio.portfoliocritocoinapplication.di.Qualifiers.NewsRetrofit
import com.myportfolio.portfoliocritocoinapplication.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworModuleNews {

    @NewsRetrofit
    @Singleton
    @Provides
    fun provideNewsRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsApiClient(@NewsRetrofit retrofit: Retrofit): ApiServiceNews {
        return retrofit.create(ApiServiceNews::class.java)
    }
}