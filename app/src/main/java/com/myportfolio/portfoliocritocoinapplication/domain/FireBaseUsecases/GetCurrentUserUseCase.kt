package com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases

import com.google.firebase.auth.FirebaseUser
import com.myportfolio.portfoliocritocoinapplication.data.firebase.services.FireAuthService
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val fireAuthService: FireAuthService
) {
    operator fun invoke(): FirebaseUser? {
        return fireAuthService.getCurrentUser()
    }
}
