package com.myportfolio.portfoliocritocoinapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.FavouritesModel
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.GetCurrentUserUseCase
import com.myportfolio.portfoliocritocoinapplication.domain.GetCoins.FetchFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val fetchFavouritesUseCase: FetchFavouritesUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,


) : ViewModel() {

    private val _facoins = MutableStateFlow<List<FavouritesModel>>(emptyList())
    val facoins: StateFlow<List<FavouritesModel>> = _facoins


    fun fetchFavourites() {
        val currentUser = getCurrentUserUseCase.invoke()
        currentUser?.let {
            fetchFavouritesUseCase(it.email ?: "", { favourites ->
                _facoins.value = favourites
            }, { error ->
                // Manejar error
            })
        }
    }


}
