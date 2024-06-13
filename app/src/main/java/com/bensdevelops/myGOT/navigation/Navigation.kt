package com.bensdevelops.myGOT.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bensdevelops.myGOT.core.base.ui.ErrorScreen
import com.bensdevelops.myGOT.ui.screens.dummyScreen.DummyScreen
import com.bensdevelops.myGOT.ui.screens.detailsScreen.DetailsScreen
import com.bensdevelops.myGOT.ui.screens.detailsScreen.DetailsScreenViewModel
import com.bensdevelops.myGOT.ui.screens.dummyScreen.DummyScreenViewModel
import com.bensdevelops.myGOT.ui.screens.homeScreen.HomeScreen
import com.bensdevelops.myGOT.ui.screens.homeScreen.HomeScreenViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            val viewModel = hiltViewModel<HomeScreenViewModel>()
            HomeScreen(
                navController = navController,
            )
        }
        composable(
            route = Screen.VibrationScreen.route
        ) {
            val vm = hiltViewModel<DummyScreenViewModel>()
            DummyScreen(navController = navController, viewModel = vm)
        }
        composable(
            route = Screen.ErrorScreen.route
        ) {
            ErrorScreen(navController = navController)
        }
        composable(
            route = Screen.DetailsScreen.route + "/{dataType}" + "/{index}",
            arguments = listOf(
                navArgument(name = "dataType") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument(name = "index") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {
            val vm = hiltViewModel<DetailsScreenViewModel>()
            DetailsScreen(viewModel = vm, navController = navController)
        }
    }
}