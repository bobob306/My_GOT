package com.bensdevelops.myGOT.mapper

import com.bensdevelops.myGOT.core.viewData.homeScreen.BookViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.CharacterViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.HomeScreenViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.HouseViewData
import com.bensdevelops.myGOT.network.model.BookModel
import com.bensdevelops.myGOT.network.model.CharacterModel
import com.bensdevelops.myGOT.network.model.HouseModel
import javax.inject.Inject

/*
The Inject annotation adds this class to something a bit like a library list
Then another class can use this class in its own constructor by looking at the massive library list and picking this class from it

The cached books only exist inside one session (if you close the app it doesn't persist currently)
If the call has been made already but now the user has no internet connection then the cached response will be used

Also if one type of data is given to the mapper, and others are not sent
(e.g. only one bit of data has been called out of the three) then the cached data will be used in the mapper

Using book.run means you dont have to keep typing book.url book.name etc
This is a bit like book.let{}, however with let if book==null the block inside {} will be skipped

This class gets called from the ViewModel on completion of a successful network request.
The value of the request is taken out from the Result.success wrapper and sent here to be
mapped to ViewData from Model
 */
class ViewDataMapper @Inject constructor() {
    private var cachedBooks: List<BookViewData>? = null
    private var cachedHouses: List<HouseViewData>? = null
    private var cachedCharacters: List<CharacterViewData>? = null
    fun map(
        books: List<BookModel>?,
        characters: List<CharacterModel>?,
        houses: List<HouseModel>?,
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
                    something = listOf(url, name, isbn),
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