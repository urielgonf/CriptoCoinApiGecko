package com.myportfolio.portfoliocritocoinapplication.Workers


import androidx.hilt.work.HiltWorkerFactory
import androidx.work.WorkerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {

    @Provides
    @Singleton
    fun provideWorkerFactory(
        workerFactory: HiltWorkerFactory
    ):  WorkerFactory {
        return workerFactory
    }
}
