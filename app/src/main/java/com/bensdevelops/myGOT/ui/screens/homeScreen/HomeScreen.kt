package com.bensdevelops.myGOT.ui.screens.homeScreen

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
import com.bensdevelops.myGOT.core.base.ViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.HomeScreenViewData
import com.bensdevelops.myGOT.ui.book.BookOverview
import com.bensdevelops.myGOT.ui.character.CharacterOverview
import com.bensdevelops.myGOT.ui.house.HouseOverview

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val nullableViewData by viewModel.viewData.observeAsState()
    val viewData = nullableViewData
    val nullableShowData by viewModel.showData.observeAsState()
    val showData = nullableShowData
    HomeScreenContent(
        showData = showData,
        viewData = viewData,
        onBooksClick = { viewModel.onBooksClick() },
        onHousesClick = { viewModel.onHousesClick() },
        onCharactersClick = { viewModel.onCharactersClick() },
        onClearClick = { viewModel.onClearClick() },
        onNavigateToDummyScreenClick = { viewModel.onNavigateToDummyScreen() },
    )
}


@Composable
private fun HomeScreenContent(
    showData: DataOptions?,
    viewData: ViewData<HomeScreenViewData>?,
    onBooksClick: () -> Unit,
    onHousesClick: () -> Unit,
    onCharactersClick: () -> Unit,
    onClearClick: () -> Unit,
    onNavigateToDummyScreenClick: () -> Unit,
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
            Button(onClick = { onClearClick.invoke()}) {
                Text(text = "Clear")
            }
            Button(onClick = { onNavigateToDummyScreenClick.invoke() }) {
                Text(text = "Navigate to dummy screen")
            }
            when (viewData) {
                is ViewData.Data -> {
                    when (showData) {
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

                else -> null
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    HomeScreenContent(null, null, {}, {}, {}, {}, {},)
}