package com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases

import android.util.Log
import com.myportfolio.portfoliocritocoinapplication.data.firebase.services.FavouriteService
import javax.inject.Inject

class UpdatePriceUseCase @Inject constructor(
    private val favouriteService: FavouriteService,
    private val getCurrentUserUseCase: GetCurrentUserUseCase

) {
    operator fun invoke(id: String,currentValue: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        val email = getCurrentUserUseCase()?.email
        if (email != null) {
            Log.d("favouritecoin","entre usecase")

            favouriteService.updatePriceFavourite(id,currentValue, email, onSuccess, onError)
        } else {
            onError(Exception("User not authenticated"))
        }
    }

}