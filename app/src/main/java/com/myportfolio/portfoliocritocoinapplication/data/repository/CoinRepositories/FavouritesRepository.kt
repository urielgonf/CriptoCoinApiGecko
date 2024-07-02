package com.myportfolio.portfoliocritocoinapplication.data.repository.CoinRepositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.FavouritesModel
import javax.inject.Inject

class FavouritesRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    fun toggleFavourite(id: String, name: String, thumbImage: String, currentValue: String, email: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        val favouritesCollection = firestore.collection("Favourites")
        val query = favouritesCollection
            .whereEqualTo("email", email)
            .whereEqualTo("id", id)

        query.get().addOnSuccessListener { querySnapshot ->
            if (querySnapshot.isEmpty) {
                val favourite = hashMapOf(
                    "id" to id,
                    "name" to name,
                    "email" to email,
                    "thumbImage" to thumbImage,
                    "currentValue" to currentValue
                )
                favouritesCollection.add(favourite)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { e -> onError(e) }
            } else {
                for (document in querySnapshot.documents) {
                    favouritesCollection.document(document.id).delete()
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { e -> onError(e) }
                }
            }
        }.addOnFailureListener { e -> onError(e) }
    }

    fun fetchFavourites(email: String, onSuccess: (List<FavouritesModel>) -> Unit, onError: (Exception) -> Unit) {
        firestore.collection("Favourites")
            .whereEqualTo("email", email)
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    onError(error)
                    return@addSnapshotListener
                }

                val documents = mutableListOf<FavouritesModel>()
                querySnapshot?.forEach { document ->
                    val myDocument = document.toObject(FavouritesModel::class.java).copy(idDoc = document.id)
                    documents.add(myDocument)
                }
                onSuccess(documents)
            }
    }
    fun updatePrice(id: String, currentValue: String, email: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        val favouritesCollection = firestore.collection("Favourites")
        val query = favouritesCollection
            .whereEqualTo("email", email)
            .whereEqualTo("id", id)
        query.get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                for (document in querySnapshot.documents) {
                    favouritesCollection.document(document.id).update("currentValue", currentValue)
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            onError(e)
                        }
                }
            } else {
                onError(Exception("No document found with the provided email and id"))
            }
        }.addOnFailureListener { e ->
            onError(e)
        }
    }


}
