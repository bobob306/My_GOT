package com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata

import androidx.compose.runtime.Immutable

@Immutable
data class FlashCardScreenViewData(
    val flashCardViewData: FlashCardViewData,
    val tags: List<String>? = null,
    val selectedTags: List<String>? = null,
    val flipped: Boolean = false,
    val sliderPosition: Float = 0f,
    val initialPosition: Float = 0f,
    val flipDirection: Float = 0f,
)

@Immutable
data class FlashCardViewData(
    val question: String,
    val answer: String,
    val tags: List<String>? = null,
)
