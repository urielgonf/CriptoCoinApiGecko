package com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases

import com.myportfolio.portfoliocritocoinapplication.data.firebase.services.FirestoreService
import javax.inject.Inject

class IsEmailUsedUseCase @Inject constructor(
    private val firestoreService: FirestoreService
) {
    suspend operator fun invoke(email: String): Boolean {
        return firestoreService.isEmailUsed(email)
    }
}