package com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases

import com.google.firebase.auth.FirebaseUser
import com.myportfolio.portfoliocritocoinapplication.data.firebase.services.FireAuthService
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val fireAuthService: FireAuthService
) {
    suspend operator fun invoke(email: String, password: String): FirebaseUser? {
        return fireAuthService.createUser(email, password)
    }
}
