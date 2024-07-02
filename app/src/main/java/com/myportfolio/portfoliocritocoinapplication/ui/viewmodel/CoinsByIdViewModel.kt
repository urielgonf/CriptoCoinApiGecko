package com.myportfolio.portfoliocritocoinapplication.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.myportfolio.portfoliocritocoinapplication.Workers.MyWorker
import com.myportfolio.portfoliocritocoinapplication.data.repository.CoinRepositories.CoinRepository
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinDetailModel
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.FavouritesModel
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.GetCurrentUserUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.UpdatePriceUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.GetCoins.FetchFavouritesUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.GetCoins.GetCoinByIdUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.GetCoins.ToggleFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CoinsByIdViewModel @Inject constructor(
    application: Application,
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    private val updatePriceUseCase: UpdatePriceUseCase,
    private val toggleFavouritesUseCase: ToggleFavouritesUseCase,
    private val fetchFavouritesUseCase: FetchFavouritesUseCase,
    private val coinRepository: CoinRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : AndroidViewModel(application) {

    private val _coin = MutableLiveData<CoinDetailModel?>()
    val coin: LiveData<CoinDetailModel?> get() = _coin

    private val _favourites = MutableLiveData<List<FavouritesModel>>()
    val favourites: LiveData<List<FavouritesModel>> get() = _favourites

    // Intervalo de tiempo en minutos entre las ejecuciones del Worker
    private var intervalMinutes = 15 // Valor por defecto

    fun setIntervalMinutes(minutes: Int) {
        intervalMinutes = minutes
    }


    fun updateIntervalAndRestartWorker(newIntervalMinutes: Int) {
        // Actualiza el intervalo en CoinsByIdViewModel
        setIntervalMinutes(newIntervalMinutes)

        // Cancela el trabajo existente y encola uno nuevo con el nuevo intervalo
        val workManager = WorkManager.getInstance(getApplication())
        workManager.cancelAllWorkByTag("monitor")

        // Enqueue new work request with updated interval
        val data = workDataOf("intervalMinutes" to newIntervalMinutes)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(newIntervalMinutes.toLong(), TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "monitor",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    fun getCoinById(id: String) {
        viewModelScope.launch {
            if (coinRepository.isCacheValid(id)) {
                _coin.value = coinRepository.getCoinById(id)
            } else {
                try {
                    val result = getCoinByIdUseCase(id)
                    _coin.value = result
                } catch (e: Exception) {
                    _coin.value = null
                    // Manejar el error apropiadamente
                }
            }
        }
    }

    fun updatePrice(id: String, currentValue: String, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            updatePriceUseCase(id, currentValue, {
                onSuccess()
            }, {
                onError()
            })
        }
    }

    fun toggleFavourite(id: String, name: String, thumbImage: String, currentValue: String, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            toggleFavouritesUseCase(id, name, thumbImage, currentValue, {
                fetchFavourites() // Actualizar la lista de favoritos después de agregar/eliminar
                if (isFavourite(id)) {
                    startPriceMonitoringWorker(id) // Iniciar el Worker si es un favorito
                    Log.d("CoinsByIdViewModel", "Notificación encolada para la moneda con ID: $id")
                } else {
                    stopPriceMonitoringWorker(id) // Detener el Worker si no es un favorito
                    Log.d("CoinsByIdViewModel", "Notificación detenida para la moneda con ID: $id")
                }
                onSuccess()
            }, {
                onError()
            })
        }
    }

    private fun startPriceMonitoringWorker(favouriteId: String) {
        val workManager = WorkManager.getInstance(getApplication())

        val data = workDataOf("favouriteId" to favouriteId, "intervalMinutes" to intervalMinutes)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(intervalMinutes.toLong(), TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "monitor_$favouriteId",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    private fun stopPriceMonitoringWorker(favouriteId: String) {
        val workManager = WorkManager.getInstance(getApplication())
        workManager.cancelUniqueWork("monitor_$favouriteId")
    }

    private fun isFavourite(id: String): Boolean {
        return _favourites.value?.any { it.id == id } == true
    }

    fun fetchFavourites() {
        val currentUser = getCurrentUserUseCase.invoke()
        currentUser?.let {
            fetchFavouritesUseCase(it.email ?: "", { favourites ->
                _favourites.value = favourites
            }, { error ->
                // Manejar error
            })
        }
    }

    fun clearCoinObserver() {
        _coin.value = null
    }
}
