package com.myportfolio.portfoliocritocoinapplication.Workers

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.GetCurrentUserUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.UpdatePriceUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.GetCoins.FetchFavouritesUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.GetCoins.GetCoinByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class MyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    private val updatePriceUseCase: UpdatePriceUseCase
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        if (!isNetworkAvailable()) {
            return Result.retry()
        }

        val favouriteId = inputData.getString("favouriteId") ?: return Result.failure()

        return withContext(Dispatchers.IO) {
            try {
                val coinDetail = getCoinByIdUseCase(favouriteId)
                coinDetail?.let {
                    val price = it.marketData.currentPrice.usd
                    val notificationService = NotificationService(applicationContext)
                    notificationService.showBasicNotification(favouriteId, price.toString())
                    updatePriceUseCase(favouriteId, price.toString(), {}, {})
                }
                Result.success()
            } catch (exception: Exception) {
                Log.e("CoinWorker", "Error getting coin details", exception)
                Result.failure()
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
