package com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata

data class FlashCardViewData(
    val question: String,
    val answer: String,
    val tags: List<String>? = null,
)
