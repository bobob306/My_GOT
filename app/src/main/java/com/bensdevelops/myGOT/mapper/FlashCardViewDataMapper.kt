package com.bensdevelops.myGOT.mapper

import com.bensdevelops.myGOT.network.repository.FlashCardDto
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata.FlashCardViewData
import javax.inject.Inject

class FlashCardViewDataMapper @Inject constructor() {
    fun map(flashCardDto: FlashCardDto): FlashCardViewData {
        return FlashCardViewData(
            question = flashCardDto.question,
            answer = flashCardDto.answer
        )
    }
}