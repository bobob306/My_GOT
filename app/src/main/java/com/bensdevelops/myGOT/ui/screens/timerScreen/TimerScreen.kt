package com.bensdevelops.myGOT.ui.screens.timerScreen

import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
    val timerData by viewModel.timerData.collectAsState()
    val event by viewModel.event.collectAsState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = timerData[0].vibrationEffect) {
        if (!timerData[0].vibrationEffect) return@LaunchedEffect
        Log.d("vib", "ration started now")
        val longArray = longArrayOf(1000, 1000, 1000, 1000)
        val vibration = VibrationEffect.createWaveform(longArray, -1)
        val effect = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(vibration)
        viewModel.resetVibrationEffect()
    }

    when (event) {
        TimerScreenEvent.InitialScreen -> {
            TimerScreenInitialContent(
                number = timerData[0].number,
                index = 0,
                onHandleValueChange = {
                    viewModel.handleValueChange(it)
                },
                onClick = { scope.launch { viewModel.onStartClick() } },
            )
        }

        TimerScreenEvent.CountdownScreen -> {
            TimerScreenContentWithTimer(
                count = timerData[0].remaining ?: 1,
                onClearClick = viewModel::resetCountdown,
                onAddTimer = viewModel::onAddTimer
            )
        }

        TimerScreenEvent.FlowScreen -> {
            MixedLazyRow(
                timerData = timerData,
                onClearClick = viewModel::resetCountdown,
                onAddTimer = viewModel::onAddTimer,
                onHandleValueChange = {
                    viewModel.handleValueChange(it)
                },
                onStartClick = { scope.launch { viewModel.onStartClick() } },
            )
        }
    }
}

@Composable
fun TimerScreenInitialContent(
    number: Int?,
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
                value = number?.toString() ?: "",
                onValueChange = { onHandleValueChange(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(bottom = SizeTokens.medium)
            )
            // Vibration Button - OneShot
            if ((number ?: 0) > 0) {
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
    onAddTimer: () -> Unit,
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
                    Button(onClick = {}) {
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

@Composable
fun MixedLazyRow(
    timerData: List<TimerData>,
    onClearClick: () -> Unit,
    onAddTimer: () -> Unit,
    onHandleValueChange: ((String) -> Unit),
    onStartClick: () -> Unit,
) {
    LazyRow(modifier = Modifier.fillMaxSize()) {
        items(items = timerData ) {
            if (it.remaining == 0) {
                TimerScreenInitialContent(
                    number = it.number,
                    index = timerData.indexOf(it),
                    onHandleValueChange = onHandleValueChange,
                    onClick = onStartClick,
                )
            } else {
                TimerScreenContentWithTimer(
                    count = it.remaining ?: 1,
                    onClearClick = onClearClick,
                    onAddTimer = onAddTimer,
                )
            }
        }
    }
}