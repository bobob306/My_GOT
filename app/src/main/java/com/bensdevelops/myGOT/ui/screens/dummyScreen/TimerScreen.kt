package com.bensdevelops.myGOT.ui.screens.dummyScreen

import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bensdevelops.myGOT.core.base.ui.GOTColumn
import com.bensdevelops.myGOT.ui.theme.SizeTokens
import kotlinx.coroutines.launch

@Composable
fun TimerScreen(
    navController: NavController,
    viewModel: TimerScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val vibrator = context.getSystemService(Vibrator::class.java)
    val vibrationEffect = viewModel.vibrationEffect.collectAsState()
    val number by viewModel.number.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val count by viewModel.count.collectAsState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = vibrationEffect.value) {
        if (!vibrationEffect.value) return@LaunchedEffect
        Log.d("vib", "ration started now")

        val effect = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(effect)
        viewModel.resetVibrationEffect()
    }

    when (loading) {
        false -> {
            TimerScreenInitialContent(
                number = number,
                index = 0,
                onHandleValueChange = {
                    viewModel.handleValueChange(it)
                },
                onClick = { scope.launch { viewModel.onClick() } },
            )
        }

        true -> {
            TimerScreenContentWithTimer(
                count = count,
                onClearClick = viewModel::resetCountdown,
            )
        }
    }
}

@Composable
fun TimerScreenInitialContent(
    number: Int,
    index: Int,
    onHandleValueChange: ((String) -> Unit),
    onClick: () -> Unit,
) {
    // Initial Content
    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Creating a Centered Column for the Vibration Buttons
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Enter your desired time")
            OutlinedTextField(
                label = { Text(text = "Enter desired time") },
                value = number.toString(),
                onValueChange = { onHandleValueChange(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(bottom = SizeTokens.medium)
            )
            // Vibration Button - OneShot
            if (number > 0) {
                Button(
                    shape = CircleShape,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxHeight(0.5f)
                        .padding(SizeTokens.medium),
                    onClick = {
                        onClick()
                    },

                    ) {
                    Text(text = "Vibration after $number seconds")
                }
            }
        }
    }
}

@Composable
fun TimerScreenContentWithTimer(
    count: Int,
    onClearClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // upper section of screen
            GOTColumn(
                modifier = Modifier
                    .fillMaxHeight(0.75f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CountDownTimer(
                    onClearClick = onClearClick,
                    count = count,
                    modifier = Modifier.fillMaxSize(),
                )
            }

            // lower section of screen
            GOTColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inverseSurface),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Button(onClick = { }) {
                        Text(text = "Add another timer")
                    }
                },
            )
        }
    }
}

@Composable
fun CountDownTimer(
    count: Int,
    onClearClick: () -> Unit,
    modifier: Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .aspectRatio(1f)
                .padding(SizeTokens.large)
                .fillMaxSize()
                .align(Alignment.Center)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = count.toString(), fontSize = 30.sp)
            Button(onClick = onClearClick) {
                Text(text = "Cancel timer")
            }
        }
    }
}