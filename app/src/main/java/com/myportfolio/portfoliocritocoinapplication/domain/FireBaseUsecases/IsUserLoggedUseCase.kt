package com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases

import com.myportfolio.portfoliocritocoinapplication.data.firebase.services.FireAuthService
import javax.inject.Inject

class IsUserLoggedUseCase @Inject constructor(
    private val fireAuthService: FireAuthService
) {
    operator fun invoke(): Boolean {
        return fireAuthService.isUserLogged()
    }
}
