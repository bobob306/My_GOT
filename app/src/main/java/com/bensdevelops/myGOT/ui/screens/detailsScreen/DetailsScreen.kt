package com.bensdevelops.myGOT.ui.screens.detailsScreen

import android.view.View
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.bensdevelops.myGOT.core.base.ViewData
import com.bensdevelops.myGOT.core.base.ui.GOTColumn
import com.bensdevelops.myGOT.core.base.ui.GenericLoading
import com.bensdevelops.myGOT.core.viewData.homeScreen.BookViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.CharacterViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.CoreViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.HouseViewData
import com.bensdevelops.myGOT.navigation.Screen
import com.bensdevelops.myGOT.ui.book.BookDetails

@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    navController: NavController,
) {
    LaunchedEffect(Unit) {
        viewModel.onStart()
    }
    val nullableViewData by viewModel.viewData.observeAsState()
    val viewData = nullableViewData

    if (viewData is ViewData.Error) {
        navController.navigate(
            Screen.ErrorScreen.route,
            navOptions = navOptions {
                launchSingleTop = true
                restoreState = false
            }
        )
    }

    DetailsScreenContent(viewData = viewData)
}

@Composable
fun DetailsScreenContent(
    viewData: ViewData<CoreViewData>?,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        when (viewData) {
            is ViewData.Loading -> GenericLoading()
            is ViewData.Data -> {
                when (viewData.content) {
                    is BookViewData -> {
                        BookDetails(book = viewData.content)
                    }

                    is CharacterViewData -> {
                        TODO()
                    }

                    is HouseViewData -> {
                        TODO()
                    }
                    else -> {}
                }
            }

            else -> Unit
        }
    }
}