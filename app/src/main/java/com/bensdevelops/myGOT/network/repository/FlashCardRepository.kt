package com.bensdevelops.myGOT.network.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

data class FlashCardDto(
    val question: String,
    val answer: String
)

interface FlashCardRepository {
    suspend fun getFlashCards(): List<FlashCardDto>
    suspend fun uploadFlashCards(data: MutableMap<String, Any>)
}

class FlashCardRepositoryImpl @Inject constructor() : FlashCardRepository {
    val firestore = Firebase.firestore
    val db = FirebaseFirestore.getInstance().collection("FlashCardList")

    private val flashDatabase = mutableListOf<FlashCardDto>()

    override suspend fun getFlashCards(): List<FlashCardDto> {
        coroutineScope {
            db.get().await().documents[0].data?.let { successfulDownload ->
                    Log.d("Great Success", "Success")
                        successfulDownload["flashCardList"].let { list ->
                            list as List<*>
                            list.forEach {
                                it as Map<*, *>
                                val flashCard = FlashCardDto(
                                    it["question"].toString(),
                                    it["answer"].toString()
                                )
                                flashDatabase.add(flashCard)
                            }
                        }
                    }
        }
        return flashDatabase
    }

    override suspend fun uploadFlashCards(data: MutableMap<String, Any>) {
        try {
            runCatching {
                db.add(data).await()
                Log.d("Success TAG", "DocumentSnapshot added")
            }
            db.add(data).await()
            Log.d("Success TAG", "DocumentSnapshot added")
        } catch (e: Exception) {
            Log.d("Error", e.toString())
        }
    }
}