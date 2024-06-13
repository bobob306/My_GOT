package com.bensdevelops.myGOT.core.base.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
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
        GOTColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(SizeTokens.large),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Please return home")
            Button(onClick = { onNavigateHome.invoke() }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Home")
            }
        }
    }
}