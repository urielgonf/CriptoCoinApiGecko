package com.myportfolio.portfoliocritocoinapplication.domain.FireBaseUsecases

import com.myportfolio.portfoliocritocoinapplication.data.firebase.services.FireAuthService
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val fireAuthService: FireAuthService
) {
    operator fun invoke() {
        fireAuthService.logout()
    }
}
