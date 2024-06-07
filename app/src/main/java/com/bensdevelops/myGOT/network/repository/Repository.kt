package com.bensdevelops.myGOT.network.repository

import com.bensdevelops.myGOT.network.model.BookModel
import com.bensdevelops.myGOT.network.model.CharacterModel
import com.bensdevelops.myGOT.network.model.HouseModel
import com.bensdevelops.myGOT.network.service.RetrofitApi
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofitApi: RetrofitApi,
) {
    private var cachedBook: BookModel? = null
    private var cachedBooks: List<BookModel>? = null
    private var cachedCharacters: List<CharacterModel>? = null
    private var cachedHouses: List<HouseModel>? = null

    suspend fun getBook(): Result<BookModel> {
        return cachedBook?.let { Result.success(it) } ?: retrofitApi.getBook().onSuccess {
            cachedBook = it
        }
    }

    suspend fun getBooks(): Result<List<BookModel>> {
        return cachedBooks?.let { Result.success(it) } ?: retrofitApi.getBooks().onSuccess {
            cachedBooks = it
        }.onFailure { Throwable("an error has occurred") }
    }

    suspend fun getCharacters(): Result<List<CharacterModel>> {
        return cachedCharacters?.let { Result.success(it) } ?: retrofitApi.getCharacters()
            .onSuccess {
                cachedCharacters = it
            }
    }

    suspend fun getHouses(): Result<List<HouseModel>> {
        return cachedHouses?.let { Result.success(it) } ?: retrofitApi.getHouses().onSuccess {
            cachedHouses = it
        }
    }
}