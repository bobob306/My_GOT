package com.bensdevelops.myGOT.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    val bookList: List<Book>,
)

@Serializable
data class CharacterDto(
    val characterList: List<Character>,
)

@Serializable
data class HouseDto(
    val houseList: List<House>,
)

@Serializable
data class Book(
    @SerialName("url") val url: String?,
    @SerialName("name") val name: String?,
    @SerialName("isbn") val isbn: String?,
    @SerialName("authors") val authors: List<String?>?,
    @SerialName("numberOfPages") val numberPages: Int?,
    @SerialName("publisher") val publisher: String?,
    @SerialName("country") val country: String?,
    @SerialName("mediaType") val mediaType: String?,
    @SerialName("released") val released: String?,
    @SerialName("characters") val characters: List<String?>?,
    @SerialName("povCharacters") val povCharacters: List<String?>?,
)

@Serializable
data class Character(
    @SerialName("url")  val url: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("gender") val gender: String? = null,
    @SerialName("culture")  val culture: String? = null,
    @SerialName("born") val born: String? = null,
    @SerialName("died") val died: String? = null,
    @SerialName("titles") val title: List<String>? = null,
    @SerialName("aliases") val aliases: List<String>? = null,
    @SerialName("father") val father: String? = null,
    @SerialName("mother") val mother: String? = null,
    @SerialName("spouse") val spouse: String? = null,
    @SerialName("allegiances") val allegiances: List<String>? = null,
    @SerialName("books") val books: List<String>? = null,
    @SerialName("povBooks") val povBooks: List<String>? = null,
    @SerialName("tvSeries") val tvSeries: List<String>? = null,
    @SerialName("playedBy") val playedBy: List<String>? = null,
)

@Serializable
data class House(
    @SerialName("url") val url: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("region") val region: String? = null,
    @SerialName("coatOfArms") val coatOfArms: String? = null,
    @SerialName("words") val words: String? = null,
    @SerialName("titles") val titles: List<String>? = null,
    @SerialName("seats") val seats: List<String>? = null,
    @SerialName("currentLord") val currentLord: String? = null,
    @SerialName("heir") val heir: String? = null,
    @SerialName("overlord") val overlord: String? = null,
    @SerialName("founded") val founded: String? = null,
    @SerialName("founder") val founder: String? = null,
    @SerialName("diedOut") val diedOut: String? = null,
    @SerialName("ancestralWeapons") val ancestralWeapons: List<String>? = null,
    @SerialName("cadetBranches") val cadetBranches: List<String>? = null,
    @SerialName("swornMembers") val swornMembers: List<String>? = null,
)