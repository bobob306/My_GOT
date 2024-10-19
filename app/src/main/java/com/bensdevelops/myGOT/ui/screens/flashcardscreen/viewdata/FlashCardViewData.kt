package com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata

data class FlashCardScreenViewData(
    val flashCardViewData: FlashCardViewData,
    val tags: List<String>? = null,
    val selectedTags: List<String>? = null,
    val flipped: Boolean? = false,
)

data class FlashCardViewData(
    val question: String,
    val answer: String,
    val tags: List<String>? = null,
)
