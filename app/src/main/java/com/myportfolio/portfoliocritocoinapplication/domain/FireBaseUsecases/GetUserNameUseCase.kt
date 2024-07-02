package com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases

import com.myportfolio.portfoliocritocoinapplication.data.firebase.services.FirestoreService
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val firestoreService: FirestoreService,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): String? {
        val userId = getCurrentUserUseCase()?.uid
        return if (userId != null) {
            firestoreService.getUserName(userId)
        } else {
            null
        }
    }
}
