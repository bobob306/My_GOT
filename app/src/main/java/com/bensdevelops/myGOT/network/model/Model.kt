package com.bensdevelops.myGOT.network.model
import com.google.gson.annotations.SerializedName


data class CharacterModel(
    @SerializedName("aliases")
    val aliases: List<String>? = null,
    @SerializedName("allegiances")
    val allegiances: List<String>? = null,
    @SerializedName("books")
    val books: List<String>? = null,
    @SerializedName("born")
    val born: String? = null,
    @SerializedName("culture")
    val culture: String? = null,
    @SerializedName("died")
    val died: String? = null,
    @SerializedName("father")
    val father: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("mother")
    val mother: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("playedBy")
    val playedBy: List<String>? = null,
    @SerializedName("povBooks")
    val povBooks: List<String>? = null,
    @SerializedName("spouse")
    val spouse: String? = null,
    @SerializedName("titles")
    val titles: List<String>? = null,
    @SerializedName("tvSeries")
    val tvSeries: List<String>? = null,
    @SerializedName("url")
    val url: String? = null,
)

data class BookModel(
    @SerializedName("authors")
    val authors: List<String?>? = null,
    @SerializedName("characters")
    val characters: List<String?>? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("isbn")
    val isbn: String? = null,
    @SerializedName("mediaType")
    val mediaType: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("numberOfPages")
    val numberOfPages: Int? = null,
    @SerializedName("povCharacters")
    val povCharacters: List<String?>? = null,
    @SerializedName("publisher")
    val publisher: String? = null,
    @SerializedName("released")
    val released: String? = null,
    @SerializedName("url")
    val url: String? = null,
)

data class HouseModel(
    @SerializedName("ancestralWeapons")
    val ancestralWeapons: List<String>? = null,
    @SerializedName("cadetBranches")
    val cadetBranches: List<String>? = null,
    @SerializedName("coatOfArms")
    val coatOfArms: String? = null,
    @SerializedName("currentLord")
    val currentLord: String? = null,
    @SerializedName("diedOut")
    val diedOut: String? = null,
    @SerializedName("founded")
    val founded: String? = null,
    @SerializedName("founder")
    val founder: String? = null,
    @SerializedName("heir")
    val heir: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("overlord")
    val overlord: String? = null,
    @SerializedName("region")
    val region: String? = null,
    @SerializedName("seats")
    val seats: List<String>? = null,
    @SerializedName("swornMembers")
    val swornMembers: List<String>? = null,
    @SerializedName("titles")
    val titles: List<String>? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("words")
    val words: String? = null,
)