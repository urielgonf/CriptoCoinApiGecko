package com.myportfolio.portfoliocritocoinapplication.data.repository.FireBase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireAuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    suspend fun login(user: String, password: String): FirebaseUser? {
        return firebaseAuth.signInWithEmailAndPassword(user, password).await().user
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    suspend fun createUser(email: String, password: String): FirebaseUser? {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
    }

    fun isUserLogged(): Boolean {
        return getCurrentUser() != null
    }

    fun getCurrentUser() = firebaseAuth.currentUser
}
