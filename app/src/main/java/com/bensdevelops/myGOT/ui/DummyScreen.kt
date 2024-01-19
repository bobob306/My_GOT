package com.bensdevelops.myGOT.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bensdevelops.myGOT.ui.homeScreen.HomeScreenViewModel

@Composable
fun DummyScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    Surface(Modifier.fillMaxSize()) {
        Text(text = "Hello world")
    }
}