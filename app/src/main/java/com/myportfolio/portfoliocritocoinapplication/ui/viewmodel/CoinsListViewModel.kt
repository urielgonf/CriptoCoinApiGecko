package com.myportfolio.portfoliocritocoinapplication.ui.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myportfolio.portfoliocritocoinapplication.domain.GetCoins.GetAllCoinsUseCase
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(
    private val getAllCoinsUseCase: GetAllCoinsUseCase

) : ViewModel() {

    private val _coins = MutableLiveData<List<CoinModel>?>()
    val coins: MutableLiveData<List<CoinModel>?> get() = _coins


    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getAllCoins() {
        viewModelScope.launch {
            try {

                val result = getAllCoinsUseCase()
                _coins.value = result
                Log.d("Coins","$result")

            } catch (e: Exception) {
                // Manejar el error apropiadamente
            }
        }
    }/////oncrate
}

