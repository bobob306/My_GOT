package com.bensdevelops.myGOT.core.base.ui

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.bensdevelops.myGOT.navigation.Screen
import com.bensdevelops.myGOT.ui.theme.SizeTokens

/*
Predominantly this screen should be called if there is an error when displaying view data or if
navigation fails.
A simple more graceful way of the app having a problem than just crashing.
 */

@Composable
fun ErrorScreen(
    navController: NavController,
) {
    ErrorScreenContent(
        onNavigateHome = {
            navController.navigate(route = Screen.HomeScreen.route)
        }
    )

}

@Composable
fun ErrorScreenContent(
    onNavigateHome: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SizeTokens.large),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Please return home")
            Button(onClick = { onNavigateHome.invoke() }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Home")
            }
        }
    }
}