package com.myportfolio.portfoliocritocoinapplication.data.firebase.services

import com.google.firebase.firestore.FirebaseFirestore
import com.myportfolio.portfoliocritocoinapplication.data.repository.FireBase.FirestoreRepository
import com.myportfolio.portfoliocritocoinapplication.data.user.model.UserModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreService @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) {
    suspend fun saveUser(userId: String, email: String, userName: String) {
        val user = UserModel(userId = userId, email = email, userName = userName)
        firestoreRepository.saveUser(user)
    }

    suspend fun getUserName(userId: String): String? {
        return firestoreRepository.getUserName(userId)
    }

    suspend fun isEmailUsed(email: String): Boolean {
        return firestoreRepository.isEmailUsed(email)
    }

    suspend fun isUserNameUsed(userName: String): Boolean {
        return firestoreRepository.isUserNameUsed(userName)
    }
}
