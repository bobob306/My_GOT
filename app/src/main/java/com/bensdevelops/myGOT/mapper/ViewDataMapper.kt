package com.bensdevelops.myGOT.mapper

import com.bensdevelops.myGOT.core.viewData.homeScreen.BookViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.CharacterViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.HomeScreenViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.HouseViewData
import com.bensdevelops.myGOT.network.model.BookModel
import com.bensdevelops.myGOT.network.model.CharacterDto
import com.bensdevelops.myGOT.network.model.CharacterModel
import com.bensdevelops.myGOT.network.model.HouseDto
import com.bensdevelops.myGOT.network.model.HouseModel
import javax.inject.Inject

class ViewDataMapper @Inject constructor() {
    var cachedBooks: List<BookViewData>? = null
    var cachedHouses: List<HouseViewData>? = null
    var cachedCharacters: List<CharacterViewData>? = null
    fun map(
        books: List<BookModel>?,
        characters: List<CharacterModel>?,
        houses: List<HouseModel>?
    ): HomeScreenViewData {

        return HomeScreenViewData(
            bookViewData = books?.let {
                cachedBooks = booksViewDataMapper(it)
                booksViewDataMapper(it)
            } ?: cachedBooks,
            houseViewData = houses?.let {
                cachedHouses = housesViewDataMapper(it)
                housesViewDataMapper(it)
            } ?: cachedHouses,
            characterViewData = characters?.let {
                cachedCharacters = charactersViewDataMapper(it)
                charactersViewDataMapper(it)
            } ?: cachedCharacters
        )
    }

    private fun booksViewDataMapper(books: List<BookModel>): List<BookViewData> {
        return books.map { book ->
            book.run {
                BookViewData(
                    url = url,
                    name = name,
                    isbn = isbn,
                    authors = authors?.map { it },
                    numberPages = numberOfPages,
                    publisher = publisher,
                    country = country,
                    mediaType = mediaType,
                    released = released,
                    characters = characters?.map { it },
                    povCharacters = povCharacters?.map { it },
                )
            }
        }
    }

    private fun housesViewDataMapper(houses: List<HouseModel>): List<HouseViewData> {
        return houses.map { house ->
            house.run {
                HouseViewData(
                    url = url,
                    name = name,
                    region = region,
                    coatOfArms = coatOfArms,
                    words = words,
                    titles = titles,
                    seats = seats,
                    currentLord = currentLord,
                    heir = heir,
                    overlord = overlord,
                    founded = founded,
                    founder = founder,
                    diedOut = diedOut,
                    ancestralWeapons = ancestralWeapons,
                    cadetBranches = cadetBranches,
                    swornMembers = swornMembers,
                )
            }
        }
    }

    private fun charactersViewDataMapper(characters: List<CharacterModel>): List<CharacterViewData> {
        return characters.map { character ->
            character.run {
                CharacterViewData(
                    url = url,
                    name = name,
                    gender = gender,
                    culture = culture,
                    born = born,
                    died = died,
                    title = titles?.map { it },
                    aliases = aliases?.map { it },
                    father = father,
                    mother = mother,
                    spouse = spouse,
                    allegiances = allegiances?.map { it },
                    books = books?.map { it },
                    povBooks = povBooks?.map { it },
                    tvSeries = tvSeries?.map { it },
                    playedBy = playedBy?.map { it },
                )
            }
        }
    }
}


class BookViewDataMapper @Inject constructor() {
    fun map(books: BookModel): BookViewData {
        val booksViewData = books.run {
            BookViewData(
                url = url,
                name = name,
                isbn = isbn,
                authors = authors?.map { it },
                numberPages = numberOfPages,
                publisher = publisher,
                country = country,
                mediaType = mediaType,
                released = released,
                characters = characters?.map { it },
                povCharacters = povCharacters?.map { it },
            )
        }
        return booksViewData
    }
}

class BooksViewDataMapper @Inject constructor() {
    fun map(books: List<BookModel>): List<BookViewData> {
        return books.map { book ->
            book.run {
                BookViewData(
                    url = url,
                    name = name,
                    isbn = isbn,
                    authors = authors?.map { it },
                    numberPages = numberOfPages,
                    publisher = publisher,
                    country = country,
                    mediaType = mediaType,
                    released = released,
                    characters = characters?.map { it },
                    povCharacters = povCharacters?.map { it },
                )
            }
        }
    }
}

class CharactersViewDataMapper @Inject constructor() {
    fun map(characters: CharacterDto): List<CharacterViewData> {
        return characters.characterList.map { character ->
            character.run {
                CharacterViewData(
                    url = url,
                    name = name,
                    gender = gender,
                    culture = culture,
                    born = born,
                    died = died,
                    title = title?.map { it },
                    aliases = aliases?.map { it },
                    father = father,
                    mother = mother,
                    spouse = spouse,
                    allegiances = allegiances?.map { it },
                    books = books?.map { it },
                    povBooks = povBooks?.map { it },
                    tvSeries = tvSeries?.map { it },
                    playedBy = playedBy?.map { it },
                )
            }
        }
    }
}

class HousesViewDataMapper @Inject constructor() {
    fun map(houses: HouseDto): List<HouseViewData> {
        return houses.houseList.map { house ->
            house.run {
                HouseViewData(
                    url = url,
                    name = name,
                    region = region,
                    coatOfArms = coatOfArms,
                    words = words,
                    titles = titles,
                    seats = seats,
                    currentLord = currentLord,
                    heir = heir,
                    overlord = overlord,
                    founded = founded,
                    founder = founder,
                    diedOut = diedOut,
                    ancestralWeapons = ancestralWeapons,
                    cadetBranches = cadetBranches,
                    swornMembers = swornMembers,
                )
            }
        }
    }
}