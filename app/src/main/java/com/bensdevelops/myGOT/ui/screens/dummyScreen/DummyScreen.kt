package com.bensdevelops.myGOT.ui.screens.dummyScreen

import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bensdevelops.myGOT.ui.theme.SizeTokens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DummyScreen(
    navController: NavController,
    viewModel: DummyScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val vibrator = context.getSystemService(Vibrator::class.java)
    val viv = viewModel.viv.collectAsState()
    val number by viewModel.number.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = viv.value) {
        if (!viv.value) return@LaunchedEffect
        Log.d("vib", "ration started now")

        val effect =
            VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(effect)
        viewModel.resetViv()
    }
    if (loading) {
        val initialCount = number
        var count by remember {
            mutableIntStateOf(initialCount)
        }
        LaunchedEffect(key1 = Unit) {
            while (count > 0) {
                delay(1000)
                count--
            }

        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .aspectRatio(1f)
                    .align(Alignment.Center)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = count.toString(), fontSize = 30.sp)
                Button(onClick = {
                    viewModel.onClearClick()
                }) {
                    Text(text = "Cancel timer")
                }
            }


        }
    } else {

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
                Spacer(modifier = Modifier.size(SizeTokens.medium))
                // Vibration Button - OneShot
                if (number > 0) {
                    Button(
                        shape = CircleShape,
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .aspectRatio(1f),
                        onClick = {
                            // Safely cancel any ongoing vibrations
                            vibrator.cancel()
                            scope.launch { viewModel.onClick() }.invokeOnCompletion {
                                // Handling vibrations for Android 8.0 (Oreo) and above
                            }
                        },

                        ) {
                        Text(text = "Vibration after $number seconds")
                    }
                }
            }
        }
    }
}