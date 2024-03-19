package com.bensdevelops.myGOT.homeScreen.presentation.mapper

import com.bensdevelops.myGOT.core.viewData.homeScreen.BookViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.CharacterViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.HouseViewData
import com.bensdevelops.myGOT.network.model.BookModel
import com.bensdevelops.myGOT.network.model.CharacterModel
import com.bensdevelops.myGOT.network.model.HouseModel

private const val AUTHOR_NAME_1 = "author_name_one"
private const val AUTHOR_NAME_2 = "author_name_two"
private const val CHARACTER_NAME_1 = "character_name_one"
private const val CHARACTER_NAME_2 = "character_name_two"
private const val COUNTRY = "country"
private const val ISBN = "isbn"
private const val MEDIA_TYPE = "media_type"
private const val NAME = "name"
private const val BOOK_NAME = "book_name"
private const val NUMBER_OF_PAGES = 1
private const val PUBLISHER = "publisher"
private const val RELEASED = "released"
private const val URL = "url"
private const val BORN = "born"
private const val DIED = "died"
private const val FATHER = "father"
private const val MOTHER = "mother"
private const val GENDER = "gender"
private const val SPOUSE = "spouse"
private const val CULTURE = "culture"
private const val TITLE_1 = "title_1"
private const val TITLE_2 = "title_2"
private const val TV_SERIES_1 = "tv_series_1"
private const val ACTOR_1 = "actor_1"
private const val ALIAS_1 = "alias_1"
private const val ALLEGIANCE_1 = "allegiance_1"
private const val COAT_OF_ARMS = "coat_of_arms"
private const val CURRENT_LORD = "current_lord"
private const val DIED_OUT = "died_out"
private const val FOUNDED = "founded"
private const val FOUNDER = "founder"
private const val HEIR = "heir"
private const val HOUSE_NAME = "house_name"
private const val OVERLORD = "overlord"
private const val REGION = "region"
private const val WORDS = "words"
private const val ANCESTRAL_WEAPON_1 = "ancestral_weapon_1"
private const val ANCESTRAL_WEAPON_2 = "ancestral_weapon_2"
private const val CADET_BRANCHES_1 = "cadet_branches_1"
private const val CADET_BRANCHES_2 = "cadet_branches_2"
private const val SEAT = "seat"
private const val SWORN_MEMBER = "sworn_member"

object TestData {

    val booksTestData = listOf(
        BookModel(
            authors = listOf(AUTHOR_NAME_1, AUTHOR_NAME_2),
            characters = listOf(CHARACTER_NAME_1, CHARACTER_NAME_2),
            country = COUNTRY,
            isbn = ISBN,
            mediaType = MEDIA_TYPE,
            name = NAME,
            numberOfPages = NUMBER_OF_PAGES,
            povCharacters = listOf(CHARACTER_NAME_1, CHARACTER_NAME_2),
            publisher = PUBLISHER,
            released = RELEASED,
            url = URL,
        )
    )

    val characterTestData = listOf(
        CharacterModel(
            aliases = listOf(ALIAS_1),
            allegiances = listOf(ALLEGIANCE_1),
            books = listOf(BOOK_NAME),
            born = BORN,
            culture = CULTURE,
            died = DIED,
            father = FATHER,
            gender = GENDER,
            mother = MOTHER,
            name = CHARACTER_NAME_1,
            playedBy = listOf(ACTOR_1),
            povBooks = listOf(BOOK_NAME),
            spouse = SPOUSE,
            titles = listOf(TITLE_1, TITLE_2),
            tvSeries = listOf(TV_SERIES_1),
            url = URL,
            )
    )

    val housesTestData = listOf(
        HouseModel(
            ancestralWeapons = listOf(ANCESTRAL_WEAPON_1, ANCESTRAL_WEAPON_2),
            cadetBranches = listOf(CADET_BRANCHES_1, CADET_BRANCHES_2),
            coatOfArms = COAT_OF_ARMS,
            currentLord = CURRENT_LORD,
            diedOut = DIED_OUT,
            founded = FOUNDED,
            founder = FOUNDER,
            heir = HEIR,
            name = HOUSE_NAME,
            overlord = OVERLORD,
            region = REGION,
            seats = listOf(SEAT),
            swornMembers = listOf(SWORN_MEMBER),
            titles = listOf(TITLE_1, TITLE_2),
            url = URL,
            words = WORDS,
            )
    )

    val booksTestViewData = listOf(
        BookViewData(
            url = URL,
            name = NAME,
            isbn = ISBN,
            authors = listOf(AUTHOR_NAME_1, AUTHOR_NAME_2),
            numberPages = NUMBER_OF_PAGES,
            publisher = PUBLISHER,
            country = COUNTRY,
            mediaType = MEDIA_TYPE,
            released = RELEASED,
            characters = listOf(CHARACTER_NAME_1, CHARACTER_NAME_2),
            povCharacters = listOf(CHARACTER_NAME_1, CHARACTER_NAME_2),
            something = listOf(URL, NAME, ISBN),
        )
    )

    val characterTestViewData = listOf(
        CharacterViewData(
            url = URL,
            name = CHARACTER_NAME_1,
            gender = GENDER,
            culture = CULTURE,
            born = BORN,
            died = DIED,
            title = listOf(TITLE_1, TITLE_2),
            aliases = listOf(ALIAS_1),
            father = FATHER,
            mother = MOTHER,
            spouse = SPOUSE,
            allegiances = listOf(ALLEGIANCE_1),
            books = listOf(BOOK_NAME),
            povBooks = listOf(BOOK_NAME),
            tvSeries = listOf(TV_SERIES_1),
            playedBy = listOf(ACTOR_1),
        )
    )

    val housesTestViewData = listOf(
        HouseViewData(
            url = URL,
            name = HOUSE_NAME,
            region = REGION,
            coatOfArms = COAT_OF_ARMS,
            words = WORDS,
            titles = listOf(TITLE_1, TITLE_2),
            seats = listOf(SEAT),
            currentLord = CURRENT_LORD,
            heir = HEIR,
            overlord = OVERLORD,
            founded = FOUNDED,
            founder = FOUNDER,
            diedOut = DIED_OUT,
            ancestralWeapons = listOf(ANCESTRAL_WEAPON_1, ANCESTRAL_WEAPON_2),
            cadetBranches = listOf(CADET_BRANCHES_1, CADET_BRANCHES_2),
            swornMembers = listOf(SWORN_MEMBER),
        )
    )

}