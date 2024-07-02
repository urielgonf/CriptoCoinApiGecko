package com.myportfolio.portfoliocritocoinapplication.data.firebase.services

import com.google.firebase.auth.FirebaseUser
import com.myportfolio.portfoliocritocoinapplication.data.repository.FireBase.FireAuthRepository
import javax.inject.Inject

class FireAuthService @Inject constructor(
    private val fireAuthRepository: FireAuthRepository
) {

    suspend fun login(user: String, password: String): FirebaseUser? {
        return fireAuthRepository.login(user, password)
    }

    fun logout() {
        fireAuthRepository.logout()
    }

    suspend fun createUser(email: String, password: String): FirebaseUser? {
        return fireAuthRepository.createUser(email, password)
    }

    fun isUserLogged(): Boolean {
        return fireAuthRepository.isUserLogged()
    }

    fun getCurrentUser() = fireAuthRepository.getCurrentUser()
}
