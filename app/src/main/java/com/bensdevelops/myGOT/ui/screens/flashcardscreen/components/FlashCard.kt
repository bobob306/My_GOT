package com.bensdevelops.myGOT.ui.screens.flashcardscreen.components

import androidx.compose.animation.core.InternalAnimationApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata.FlashCardScreenViewData

@Composable
fun FlashCard(
    viewData: FlashCardScreenViewData,
    onNextQuestionClick: () -> Unit,
    onNavigateToHome: () -> Unit,
    onFilterClicked: () -> Unit,
    onDragStopped: (Float) -> Unit,
    onDragStarted: (Float) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        FlippableCard(
            modifier = Modifier
                .fillMaxSize(1f)
                .weight(1f, false),
            viewData = viewData,
            onNextQuestionClick = onNextQuestionClick,
            onDragStopped = onDragStopped,
            onDragStarted = onDragStarted,
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

@OptIn(InternalAnimationApi::class)
@Composable
fun FlippableCard(
    modifier: Modifier,
    viewData: FlashCardScreenViewData,
    onNextQuestionClick: () -> Unit,
    onDragStopped: (Float) -> Unit,
    onDragStarted: (Float) -> Unit,
) {
    val transition = updateTransition(
        targetState = viewData.flipped,
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
        if (it && viewData.flipped) {
            viewData.flipDirection
        }
        else {
            viewData.flipDirection
        }
    }
    Column(
        modifier = modifier
            .graphicsLayer {
                rotationY = if (
                    viewData.flipped &&
                    (transition.playTimeNanos > 500
                            || !transition.isRunning)
                )
                     180f else 0f
            }
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
                    state = rememberDraggableState {

                    },
                    orientation = Orientation.Horizontal,
                    onDragStarted = {
                        onDragStarted(it.x)
                    },
                    onDragStopped = {
                        onDragStopped(it)
                    }
                )
        ) {
            if (!viewData.flipped) {
                QuestionContent(viewData.flashCardViewData)
            } else {
                AnswerContent(
                    viewData.flashCardViewData.answer,
                    onNextQuestionClick
                )
            }
        }
    }
}