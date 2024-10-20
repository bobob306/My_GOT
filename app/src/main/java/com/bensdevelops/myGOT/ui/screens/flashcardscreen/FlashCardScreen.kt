package com.bensdevelops.myGOT.ui.screens.flashcardscreen

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.bensdevelops.myGOT.core.base.ViewData
import com.bensdevelops.myGOT.core.base.ui.GenericLoading
import com.bensdevelops.myGOT.navigation.Screen
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata.FlashCardScreenViewData
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata.FlashCardViewData
import kotlinx.coroutines.launch

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
                viewData = (viewData as ViewData.Data<FlashCardScreenViewData>).content,
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
                },
                onCardClick = { viewModel.onFlip() },
                onItemClick = { viewModel.onTagClick(it) },
                updateQuestions = { viewModel.filterFlashCardsByTags() },
            )
        }
    }
}

@Preview
@Composable
private fun FlashCardScreenPreview() {

    FlashCardScreenContent(
        viewData = FlashCardScreenViewData(
            flashCardViewData = FlashCardViewData(
                question = "Question",
                answer = "Answer",
                tags = listOf("Tag1", "Tag2")
            ),
            tags = listOf("Tag1", "Tag2"),
            selectedTags = listOf("Tag1"),
            flipped = false,
        ),
        onNavigateToHome = {},
        onNextQuestionClick = {},
        onCardClick = {},
        onItemClick = {}
    ) {}
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FlashCardScreenContent(
    viewData: FlashCardScreenViewData,
    onNavigateToHome: () -> Unit,
    onNextQuestionClick: () -> Unit,
    onCardClick: () -> Unit,
    onItemClick: (String) -> Unit,
    updateQuestions: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            FlashCard(
                viewData = viewData,
                onNextQuestionClick = onNextQuestionClick,
                onCardClick = onCardClick,
                onNavigateToHome = onNavigateToHome,
                onFilterClicked = { showBottomSheet = !showBottomSheet },
            )
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                updateQuestions.invoke()
            },
            sheetState = sheetState
        ) {
            Surface(
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column(
                    Modifier
                        .verticalScroll(state = rememberScrollState())
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    FlowRow {
                        viewData.tags?.forEach {
                            Button(
                                colors = ButtonColors(
                                    containerColor = if (viewData.selectedTags?.contains(it) == true)
                                        MaterialTheme.colorScheme.primary
                                    else
                                        MaterialTheme.colorScheme.inversePrimary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                    disabledContentColor = Color.Unspecified,
                                    disabledContainerColor = Color.Unspecified,
                                ),
                                onClick = {
                                    onItemClick(it)
                                }
                            ) {
                                Text(text = it)
                            }
                        }
                    }
                    // Sheet content
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }
                    ) {
                        Text("Hide bottom sheet")
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCard(
    viewData: FlashCardScreenViewData,
    onNextQuestionClick: () -> Unit,
    onCardClick: () -> Unit,
    onNavigateToHome: () -> Unit,
    onFilterClicked: () -> Unit,
) {

    val transition = updateTransition(
        targetState = viewData.flipped ?: false,
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Card(
            onClick = { onCardClick() },
            modifier = Modifier
                .weight(1f, false)
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
            ) {
                if (rotation < 90f) {
                    QuestionContent(flashCardViewData = viewData.flashCardViewData)
                } else {
                    // Apply rotation to the back content
                    AnswerContent(
                        answer = viewData.flashCardViewData.answer,
                        onNextQuestionClick,
                    )
                }
            }
        }
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
    }
}

@Composable
fun QuestionContent(flashCardViewData: FlashCardViewData) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
        ,
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
            text = flashCardViewData.question,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            text = ("Tags = " + flashCardViewData.tags.toString()) ?: "",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun AnswerContent(answer: String, onNextQuestionClick: () -> Unit) {
    Column(
        modifier = Modifier
            .graphicsLayer { rotationY = 180f }
            .wrapContentHeight()
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
                .weight(1f, false)
                .wrapContentHeight()
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