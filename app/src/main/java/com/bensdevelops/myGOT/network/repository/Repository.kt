package com.bensdevelops.myGOT.network.repository

import com.bensdevelops.myGOT.network.model.BookModel
import com.bensdevelops.myGOT.network.model.CharacterModel
import com.bensdevelops.myGOT.network.model.HouseModel
import com.bensdevelops.myGOT.network.service.RetrofitApi
import javax.inject.Inject

interface Repository {
    suspend fun getBook(index: String): Result<BookModel>
    suspend fun getHouse(index: String): Result<HouseModel>
    suspend fun getCharacter(index: String): Result<CharacterModel>
    suspend fun getBooks(): Result<List<BookModel>>
    suspend fun getCharacters(): Result<List<CharacterModel>>
    suspend fun getHouses(): Result<List<HouseModel>>
}

class RepositoryImpl @Inject constructor(
    private val retrofitApi: RetrofitApi,
) : Repository {
    private var cachedBooks: List<BookModel>? = null
    private var cachedCharacters: List<CharacterModel>? = null
    private var cachedHouses: List<HouseModel>? = null

    override suspend fun getBook(index: String): Result<BookModel> {
        return retrofitApi.getBook(index)
    }

    override suspend fun getHouse(index: String): Result<HouseModel> {
        return retrofitApi.getHouse(index)
    }

    override suspend fun getCharacter(index: String): Result<CharacterModel> {
        return retrofitApi.getCharacter(index)
    }

    override suspend fun getBooks(): Result<List<BookModel>> {
        return cachedBooks?.let { Result.success(it) } ?: retrofitApi.getBooks().onSuccess {
            cachedBooks = it
        }.onFailure { Throwable("an error has occurred") }
    }

    override suspend fun getCharacters(): Result<List<CharacterModel>> {
        return cachedCharacters?.let { Result.success(it) } ?: retrofitApi.getCharacters()
            .onSuccess {
                cachedCharacters = it
            }
    }

    override suspend fun getHouses(): Result<List<HouseModel>> {
        return cachedHouses?.let { Result.success(it) } ?: retrofitApi.getHouses().onSuccess {
            cachedHouses = it
        }
    }
}