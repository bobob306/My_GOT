package com.bensdevelops.myGOT.ui.screens.flashcardscreen

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.bensdevelops.myGOT.core.base.ViewData
import com.bensdevelops.myGOT.core.base.ui.GenericLoading
import com.bensdevelops.myGOT.navigation.Screen
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata.FlashCardViewData

@Composable
fun FlashCardScreen(
    navController: NavController,
    viewModel: FlashCardViewModel = hiltViewModel()
) {
    val viewData by viewModel.viewData.observeAsState()
    var flipped by remember { mutableStateOf(false) }
    when (viewData) {
        is ViewData.Error, null -> {
            navController.navigate(
                Screen.ErrorScreen.route,
                navOptions = navOptions {
                    launchSingleTop = true
                    restoreState = false
                }
            )
        }

        is ViewData.Loading -> {
            GenericLoading()
        }

        is ViewData.Data -> {
            FlashCardScreenContent(
                viewData = (viewData as ViewData.Data<FlashCardViewData>).content,
                flipped = flipped,
                onNavigateToHome = {
                    navController.navigate(
                        route = Screen.HomeScreen.route,
                        navOptions = navOptions {
                            launchSingleTop = true
                            restoreState = false
                        }
                    )
                },
                onNextQuestionClick = {
                    viewModel.onNextQuestionClick()
                    flipped = !flipped
                },
                onCardClick = { flipped = !flipped },
            )
        }
    }
}

@Composable
fun FlashCardScreenContent(
    viewData: FlashCardViewData,
    flipped: Boolean,
    onNavigateToHome: () -> Unit,
    onNextQuestionClick: () -> Unit,
    onCardClick: () -> Unit,
) {

    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            FlashCard(viewData, onNextQuestionClick, onCardClick, onNavigateToHome, flipped)
        }
    }

}

@Composable
fun FlashCard(
    viewData: FlashCardViewData,
    onNextQuestionClick: () -> Unit,
    onCardClick: () -> Unit,
    onNavigateToHome: () -> Unit,
    flipped: Boolean
) {

    val transition = updateTransition(
        targetState = flipped,
        label = "flash card flip"
    )
    val rotation by transition.animateFloat(
        label = "flash card flip",
        transitionSpec = {
            if (targetState) {
                tween(durationMillis = 500)
            } else {
                tween(durationMillis = 500)
            }
        }
    ) {
        if (it) 180f else 0f
    }
    Column {
        Card(
            onClick = { onCardClick() },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth()
                .padding(16.dp)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12f * density
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
                if (rotation < 90f) {
                    QuestionContent(question = viewData.question)
                } else {
                    // Apply rotation to the back content
                    Column(modifier = Modifier.graphicsLayer { rotationY = 180f }) {
                        AnswerContent(answer = viewData.answer, onNextQuestionClick)
                    }
                }
            }
        }
        Button(
            onNavigateToHome,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Navigate Home",
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
fun QuestionContent(question: String) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp),
            )
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(16.dp),
            text = question,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun AnswerContent(answer: String, onNextQuestionClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp),
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(0.9f)
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp),
                    ),
                text = answer,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Button(
            onClick = {
                onNextQuestionClick()
            }, modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .wrapContentSize()
        ) {
            Text(text = "Next Question")
        }
    }
}