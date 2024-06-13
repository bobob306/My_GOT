package com.bensdevelops.myGOT.ui.screens.dummyScreen

import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DummyScreen(
    navController: NavController,
    viewModel: DummyScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val vibrator = context.getSystemService(Vibrator::class.java)
    val number by viewModel.number.collectAsState()

    Surface(Modifier.fillMaxSize()) {

        // Creating a Centered Column for the Vibration Buttons
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Enter your desired time")
            OutlinedTextField(
                label = { Text(text = "Enter desired time") },
                value = number.toString(),
                onValueChange = viewModel::handleValueChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            // Vibration Button - OneShot
            if (number > 0) {
                Button(onClick = {
                    // Safely cancel any ongoing vibrations
                    vibrator.cancel()
                    viewModel.onClick()
                    // Handling vibrations for Android 8.0 (Oreo) and above
                    Log.d("vib", "ration started now")
                    val effect =
                        VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
                    vibrator.vibrate(effect)
                }

                ) {
                    Text(text = "Vibration after $number seconds")
                }
            }
        }
    }
}