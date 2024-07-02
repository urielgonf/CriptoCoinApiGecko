package com.myportfolio.portfoliocritocoinapplication.data.repository.FireBase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.myportfolio.portfoliocritocoinapplication.data.user.model.UserModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun saveUser(user: UserModel) {
        firestore.collection("Users").document(user.userId).set(user).await()
    }

    suspend fun getUserName(userId: String): String? {
        val querySnapshot = firestore.collection("Users")
            .whereEqualTo("userId", userId)
            .get()
            .await()

        return if (querySnapshot.isEmpty) {
            null
        } else {
            querySnapshot.documents[0].getString("userName")
        }
    }
    suspend fun isEmailUsed(email: String): Boolean {
        val querySnapshot = firestore.collection("Users")
            .whereEqualTo("email", email)
            .get()
            .await()

        return !querySnapshot.isEmpty
    }

    suspend fun isUserNameUsed(userName: String): Boolean {
        val querySnapshot = firestore.collection("Users")
            .whereEqualTo("userName", userName)
            .get()
            .await()

        return !querySnapshot.isEmpty
    }
}

