package com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases

import com.myportfolio.portfoliocritocoinapplication.data.firebase.services.FirestoreService
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val firestoreService: FirestoreService
) {
    suspend operator fun invoke(userId: String, email: String, userName: String) {
        firestoreService.saveUser(userId, email, userName)
    }
}
