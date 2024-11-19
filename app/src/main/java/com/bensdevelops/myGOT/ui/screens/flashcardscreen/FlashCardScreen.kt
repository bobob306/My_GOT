package com.bensdevelops.myGOT.ui.screens.flashcardscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.bensdevelops.myGOT.core.base.ViewData
import com.bensdevelops.myGOT.core.base.ui.GenericLoading
import com.bensdevelops.myGOT.navigation.Screen
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.components.FlashCard
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.components.FlashCardModalContent
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata.FlashCardScreenViewData
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata.FlashCardViewData
import kotlinx.coroutines.launch

@Composable
fun FlashCardScreen(
    navController: NavController,
    viewModel: FlashCardViewModel = hiltViewModel()
) {
    val viewData by viewModel.viewData.observeAsState()
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
                onItemClick = { viewModel.onTagClick(it) },
                updateQuestions = { viewModel.filterFlashCardsByTags() },
                onDragStopped = { viewModel.onDragStopped(it) },
                onDragStarted = {viewModel.onDragStarted(it)}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCardScreenContent(
    viewData: FlashCardScreenViewData,
    onNavigateToHome: () -> Unit,
    onNextQuestionClick: () -> Unit,
    onItemClick: (String) -> Unit,
    updateQuestions: () -> Unit,
    onDragStopped: (Float) -> Unit,
    onDragStarted: (Float) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    fun onHideButtonClick() {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = false
            }
        }
    }
    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            FlashCard(
                viewData = viewData,
                onNextQuestionClick = onNextQuestionClick,
                onNavigateToHome = onNavigateToHome,
                onFilterClicked = { showBottomSheet = !showBottomSheet },
                onDragStopped = onDragStopped,
                onDragStarted = onDragStarted,
            )
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                updateQuestions.invoke()
            },
            sheetState = sheetState,
            content = { FlashCardModalContent(viewData, onItemClick) { onHideButtonClick() } }
        )
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
        onItemClick = {},
        updateQuestions = {},
        onDragStopped = {},
        onDragStarted = {},
    )
}