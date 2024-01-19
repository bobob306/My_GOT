package com.bensdevelops.myGOT.core.base.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.bensdevelops.myGOT.navigation.Screen

@Composable
fun ErrorScreen(
    navController: NavController,
    onClick: Unit = navController.navigate(Screen.HomeScreen.route),
) {
    Surface(
        modifier =
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Text(text = "Please return home")
            Button(onClick = { onClick }){
                Text(text = "Home")
            }
        }
    }
}