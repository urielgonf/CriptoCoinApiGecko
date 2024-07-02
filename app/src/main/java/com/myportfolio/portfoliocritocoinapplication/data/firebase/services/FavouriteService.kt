package com.myportfolio.portfoliocritocoinapplication.data.firebase.services

import android.util.Log
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.FavouritesModel
import com.myportfolio.portfoliocritocoinapplication.data.repository.CoinRepositories.FavouritesRepository
import javax.inject.Inject

class FavouriteService @Inject constructor(
    private val favouriteRepository: FavouritesRepository
) {

    fun toggleFavourite(id: String, name: String, thumbImage: String, currentValue: String, email: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        favouriteRepository.toggleFavourite(id, name, thumbImage, currentValue, email, onSuccess, onError)
    }

    fun fetchFavourites(email: String, onSuccess: (List<FavouritesModel>) -> Unit, onError: (Exception) -> Unit) {
        favouriteRepository.fetchFavourites(email, onSuccess, onError)
    }
    fun updatePriceFavourite(id: String,currentValue: String,email: String,onSuccess: () -> Unit, onError: (Exception) -> Unit){
        Log.d("favouritecoin","entre service")
        favouriteRepository.updatePrice(id,currentValue,email,onSuccess, onError)
    }
}