package com.bensdevelops.myGOT.ui.screens.flashcardscreen.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.AnswerContent
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.QuestionContent
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata.FlashCardScreenViewData
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata.FlashCardViewData

@Composable
fun FlashCard(
    viewData: FlashCardScreenViewData,
    onNextQuestionClick: () -> Unit,
    onCardClick: () -> Unit,
    onNavigateToHome: () -> Unit,
    onFilterClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        FlashCardV2(
            modifier = Modifier.fillMaxSize(1f).weight(1f, false),
            viewData.flipped ?: false,
            viewData.flashCardViewData,
            onCardClick,
            onNextQuestionClick,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContentColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified,
                ),
                onClick = onFilterClicked,
                modifier = Modifier
            ) {
                Text(
                    text = "Filter questions",
                    color = MaterialTheme.colorScheme.onSecondary,
                )
            }
            Button(
                onNavigateToHome,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = "Navigate Home",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun FlashCardV2(
    modifier: Modifier,
    flippedViewData: Boolean = false,
    flashCardViewData: FlashCardViewData,
    onCardClick: () -> Unit,
    onNextQuestionClick: () -> Unit,
) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    val initialPosition by remember { mutableFloatStateOf(0f) }
    var flipped by remember { mutableStateOf(flippedViewData) }
    var flipDirection by remember { mutableFloatStateOf(0f) }
    val transition = updateTransition(
        targetState = flipped,
        label = "flash card flip"
    )
    val rotation by transition.animateFloat(
        label = "flash card flip",
        transitionSpec = {
            if (targetState) {
                tween(durationMillis = 1000)
            } else {
                tween(durationMillis = 1000)
            }
        }
    ) {
        onCardClick()
        if (it && flipped) flipDirection else flipDirection
    }
    Column(
        modifier = modifier
            .graphicsLayer { if (flipped && !transition.isRunning) rotationY = 180f }
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        Card(
            modifier = Modifier
                .weight(1f, false)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp)
                .fillMaxWidth()
                .graphicsLayer {
                    rotationY = rotation
                }
                .draggable(
                    state = rememberDraggableState { },
                    orientation = Orientation.Horizontal,
                    onDragStopped = {
                        sliderPosition = it / 10 - initialPosition
                        when {
                            sliderPosition > 0.5f -> {
                                flipDirection += 180f
                                flipped = !flipped
                                sliderPosition = 0f
                            }

                            sliderPosition < -0.5f -> {
                                flipDirection -= 180f
                                flipped = !flipped
                                sliderPosition = 0f
                            }
                        }
                    }
                )
        ) {
            if (!flipped) {
                QuestionContent(flashCardViewData)
            } else {
                AnswerContent(flashCardViewData.answer, onNextQuestionClick, transition)
            }
        }
    }
}