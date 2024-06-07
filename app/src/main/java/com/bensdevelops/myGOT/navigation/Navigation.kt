package com.bensdevelops.myGOT.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bensdevelops.myGOT.core.base.ui.ErrorScreen
import com.bensdevelops.myGOT.ui.DummyScreen
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
            route = Screen.DummyScreen.route
        ) {
            val vm = hiltViewModel<HomeScreenViewModel>()
            DummyScreen(navController = navController, viewModel = vm)
        }
        composable(
            route = Screen.ErrorScreen.route
        ) {
            ErrorScreen(navController = navController)
        }
    }
}