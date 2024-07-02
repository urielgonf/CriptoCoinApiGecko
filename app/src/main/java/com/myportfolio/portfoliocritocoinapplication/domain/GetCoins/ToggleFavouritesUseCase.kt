package com.myportfolio.portfoliocritocoinapplication.domain.GetCoins

import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.FavouritesModel
import com.myportfolio.portfoliocritocoinapplication.data.firebase.services.FavouriteService
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.GetCurrentUserUseCase
import javax.inject.Inject

class ToggleFavouritesUseCase @Inject constructor(
    private val favouriteService: FavouriteService,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    operator fun invoke(id: String, name: String, thumbImage: String, currentValue: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        val email = getCurrentUserUseCase()?.email
        if (email != null) {
            favouriteService.toggleFavourite(id, name, thumbImage, currentValue, email, onSuccess, onError)
        } else {
            onError(Exception("User not authenticated"))
        }
    }




}
