package com.bensdevelops.myGOT.ui.screens.homeScreen

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.bensdevelops.myGOT.core.base.ViewData
import com.bensdevelops.myGOT.core.base.ui.ErrorScreen
import com.bensdevelops.myGOT.core.base.ui.GenericLoading
import com.bensdevelops.myGOT.core.viewData.homeScreen.HomeScreenViewData
import com.bensdevelops.myGOT.navigation.Screen
import com.bensdevelops.myGOT.ui.book.BookOverview
import com.bensdevelops.myGOT.ui.character.CharacterOverview
import com.bensdevelops.myGOT.ui.house.HouseOverview

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val viewData by viewModel.viewData.observeAsState()

    if (viewData is ViewData.Error) {
        navController.navigate(
            Screen.ErrorScreen.route,
            navOptions = navOptions {
                launchSingleTop = true
                restoreState = false
            }
        )
        viewModel.reset()
    }

    HomeScreenContent(
        viewData = viewData,
        onBooksClick = { viewModel.onBooksClick() },
        onHousesClick = { viewModel.onHousesClick() },
        onCharactersClick = { viewModel.onCharactersClick() },
        onClearClick = { viewModel.onClearClick() },
        onNavigateToDummyScreenClick = {
            viewModel.onNavigateToDummyScreen()
            navController.navigate(Screen.DummyScreen.route)
        },
        onNavigateToErrorScreenClick = {
            navController.navigate(
                Screen.ErrorScreen.route,
                navOptions = navOptions {
                    launchSingleTop = true
                    restoreState = false
                    popUpTo(Screen.HomeScreen.route) {
                        inclusive = true
                    }
                }
            )
        },
    )
}


@Composable
private fun HomeScreenContent(
    viewData: ViewData<HomeScreenViewData>?,
    onBooksClick: () -> Unit,
    onHousesClick: () -> Unit,
    onCharactersClick: () -> Unit,
    onClearClick: () -> Unit,
    onNavigateToDummyScreenClick: () -> Unit,
    onNavigateToErrorScreenClick: () -> Unit,
) {
    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = "View Books, Houses or Characters",
            )
            Button(onClick = {
                onBooksClick.invoke()
            }) {
                Text(text = "Books")
            }
            Button(onClick = {
                onHousesClick.invoke()
            }) {
                Text(text = "Houses")
            }
            Button(onClick = {
                onCharactersClick.invoke()
            }) {
                Text(text = "Characters")
            }
            if (viewData is ViewData.Data) {
                viewData.content.showData?.let {
                    Button(onClick = { onClearClick.invoke() }) {
                        Text(text = "Clear")
                    }
                }
            }
            Button(onClick = {
                onNavigateToDummyScreenClick.invoke()
            }) {
                Text(text = "Navigate to dummy screen")
            }
            Button(onClick = {
                onNavigateToErrorScreenClick.invoke()
            }) {
                Text(text = "Navigate to error screen")
            }
            when (viewData) {
                is ViewData.Data -> {
                    when (viewData.content.showData) {
                        DataOptions.BOOKS -> viewData.content.bookViewData?.let { books ->
                            BookOverview(books = books)
                        }

                        DataOptions.HOUSES -> viewData.content.houseViewData?.let { houses ->
                            HouseOverview(houses = houses)
                        }

                        DataOptions.CHARACTERS -> viewData.content.characterViewData?.let { characters ->
                            CharacterOverview(characters = characters)
                        }

                        else -> {}
                    }
                }

                is ViewData.Loading -> GenericLoading()

                else -> null
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    HomeScreenContent(null, {}, {}, {}, {}, {}, {})
}