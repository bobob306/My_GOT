package com.bensdevelops.myGOT.ui.screens.timerScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bensdevelops.myGOT.ui.screens.timerScreen.TimerScreenEvent.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Timer
import javax.inject.Inject

data class TimerData(
    val number: Int? = 0,
    val remaining: Int? = 0,
    val vibrationEffect: Boolean = false,
    val loading: Boolean = false
)

sealed class TimerScreenEvent() {
    data object InitialScreen : TimerScreenEvent()
    data object CountdownScreen : TimerScreenEvent()
    data object FlowScreen : TimerScreenEvent()
    data object Vibration : TimerScreenEvent()
}

class TimerScreenViewModel @Inject constructor() : ViewModel() {
    private var _timerData = MutableStateFlow(listOf(TimerData()))
    val timerData: StateFlow<List<TimerData>> = _timerData.asStateFlow()

    private var _event = MutableStateFlow<TimerScreenEvent>(InitialScreen)
    val event = _event.asStateFlow()

    private var job: Job? = null

    private var endTime: LocalDateTime? = null

    fun handleValueChange(newText: String) {
        val newValue = if (newText == "0") 0 else newText.filter { it.isDigit() }
            .toIntOrNull() ?: 0
        _timerData.value = listOf(timerData.value.first().copy(number = newValue))
    }

    fun resetVibrationEffect() {
        _timerData.value = listOf(timerData.value[0].copy(vibrationEffect = false))
        _event.value = InitialScreen
    }

    fun onStartClick() {
        _event.value = CountdownScreen
        Log.d("vib", "ration waiting begins")
        startCountdown(initialValue = timerData.value[0].number ?: 1)
    }

    private fun startCountdown(initialValue: Int) {
        job = viewModelScope.launch {
            endTime = LocalDateTime.now().plusSeconds(initialValue.toLong())
            _timerData.value = listOf(timerData.value[0].copy(remaining = initialValue))
            while (endTime!! > LocalDateTime.now() && (_timerData.value[0].remaining ?: 0) > 0) {
                _timerData.value = listOf(
                    timerData.value[0].copy(
                        remaining = endTime!!.toEpochSecond(ZoneOffset.UTC).toInt() -
                                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toInt()
                    )
                )
                delay(100) // Update every100ms for smoother countdown
            }
            if ((timerData.value[0].remaining ?: 0) > 0) {
                _timerData.value = listOf(timerData.value[0].copy(remaining = 0))
            }
            _timerData.value =
                listOf(timerData.value[0].copy(vibrationEffect = true, loading = false))
            Log.d("this", "got hit")
            _event.value = Vibration
        }
    }

    fun onAddTimer() {
        _timerData.value = timerData.value.plus(TimerData())
        Log.d("number of timers = ", timerData.value.size.toString())
        _event.value = FlowScreen
    }

    fun resetCountdown() {
        job?.cancel()
        _timerData.value =
            listOf(timerData.value[0].copy(remaining = 0, vibrationEffect = false, loading = false))
        _event.value = InitialScreen
    }

}