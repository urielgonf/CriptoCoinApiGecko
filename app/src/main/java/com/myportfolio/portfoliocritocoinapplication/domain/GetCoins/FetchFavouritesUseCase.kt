package com.myportfolio.portfoliocritocoinapplication.domain.GetCoins

import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.FavouritesModel
import com.myportfolio.portfoliocritocoinapplication.data.firebase.services.FavouriteService
import com.myportfolio.portfoliocritocoinapplication.data.repository.CoinRepositories.FavoritesRoomRepository
import com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases.GetCurrentUserUseCase
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class FetchFavouritesUseCase @Inject constructor(
    private val favouriteService: FavouriteService,
    private val favoritesRoomRepository: FavoritesRoomRepository
) {
    operator fun invoke(email: String, onSuccess: (List<FavouritesModel>) -> Unit, onError: (Exception) -> Unit) {
        favouriteService.fetchFavourites(email, onSuccess, onError)
    }


    operator fun  invoke(): Flow<List<FavouritesModel>> = favoritesRoomRepository.coins


}
