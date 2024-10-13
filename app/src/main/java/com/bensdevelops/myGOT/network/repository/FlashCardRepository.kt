package com.bensdevelops.myGOT.network.repository

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

data class FlashCardDto(
    val question: String,
    val answer: String,
    val tags: List<String>? = null,
)

interface FlashCardRepository {
    suspend fun getFlashCards(): List<FlashCardDto>
    suspend fun uploadFlashCards(data: MutableMap<String, Any>)
}

class FlashCardRepositoryImpl @Inject constructor(
    private val cr: CollectionReference
) : FlashCardRepository {

    private val flashDatabase = mutableListOf<FlashCardDto>()

    override suspend fun getFlashCards(): List<FlashCardDto> {
        coroutineScope {
            cr.get().await().documents[0].data?.let { successfulDownload ->
                successfulDownload["flashCardList"].let { list ->
                    list as List<*>
                    list.forEach {
                        it as Map<*, *>
                        val flashCard = FlashCardDto(
                            it["question"].toString(),
                            it["answer"].toString(),
                            it["tags"] as List<String>?,
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
            cr.add(data).await()
            Log.d("Success TAG", "DocumentSnapshot added")
        } catch (e: Exception) {
            Log.d("Error", e.toString())
        }
    }
}