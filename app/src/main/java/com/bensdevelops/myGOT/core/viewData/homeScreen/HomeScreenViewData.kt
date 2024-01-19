package com.bensdevelops.myGOT.core.viewData.homeScreen

data class HomeScreenViewData(
    val bookViewData: List<BookViewData>? = null,
    val houseViewData: List<HouseViewData>? = null,
    val characterViewData: List<CharacterViewData>? = null,
)

data class BookViewData(
    val url: String?,
    val name: String?,
    val isbn: String?,
    val authors: List<String?>?,
    val numberPages: Int?,
    val publisher: String?,
    val country: String?,
    val mediaType: String?,
    val released: String?,
    val characters: List<String?>?,
    val povCharacters: List<String?>?,
)

data class HouseViewData(
    val url: String? = null,
    val name: String? = null,
    val region: String? = null,
    val coatOfArms: String? = null,
    val words: String? = null,
    val titles: List<String>? = null,
    val seats: List<String>? = null,
    val currentLord: String? = null,
    val heir: String? = null,
    val overlord: String? = null,
    val founded: String? = null,
    val founder: String? = null,
    val diedOut: String? = null,
    val ancestralWeapons: List<String>? = null,
    val cadetBranches: List<String>? = null,
    val swornMembers: List<String>? = null,
)

data class CharacterViewData(
    val url: String? = null,
    val name: String? = null,
    val gender: String? = null,
    val culture: String? = null,
    val born: String? = null,
    val died: String? = null,
    val title: List<String>? = null,
    val aliases: List<String>? = null,
    val father: String? = null,
    val mother: String? = null,
    val spouse: String? = null,
    val allegiances: List<String>? = null,
    val books: List<String>? = null,
    val povBooks: List<String>? = null,
    val tvSeries: List<String>? = null,
    val playedBy: List<String>? = null,
)
