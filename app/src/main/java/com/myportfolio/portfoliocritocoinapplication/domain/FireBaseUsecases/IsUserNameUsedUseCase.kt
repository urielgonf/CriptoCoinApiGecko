package com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases

import com.myportfolio.portfoliocritocoinapplication.data.firebase.services.FirestoreService
import javax.inject.Inject

class IsUserNameUsedUseCase @Inject constructor(
    private val firestoreService: FirestoreService
) {
    suspend operator fun invoke(userName: String): Boolean {
        return firestoreService.isUserNameUsed(userName)
    }
}
