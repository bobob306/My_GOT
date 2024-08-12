package com.bensdevelops.myGOT.ui.screens.timerScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bensdevelops.myGOT.ui.screens.timerScreen.TimerScreenEvents.*
import kotlinx.coroutines.CompletionHandler
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

data class TimeData(
    var number: Int = 0,
    var count: Int = 0,
    var vibrationEffect: Boolean = false,
    var loading: Boolean = false,
    var endTime: LocalDateTime? = null,
)

sealed class TimerScreenEvents() {
    object StartVibration : TimerScreenEvents()
}

class TimerScreenViewModel @Inject constructor() : ViewModel() {
    private var _timerData = MutableStateFlow<List<TimeData>>(mutableListOf(TimeData()))
    val timerData = _timerData.asStateFlow()

    private var job: Job? = null

    private var _events = MutableStateFlow<TimerScreenEvents?>(null)
    val events = _events.asStateFlow()

    fun handleItemValueChange(index: Int, newText: String) {
        // check if the previous number was 0, and remove that 0 so that 1 does not accidentally
        // become 10 by appending it with the existing 0
        if (timerData.value[index].number == 0) {
            val newValue = if (newText == "0") 0 else newText.filter { it.isDigit() }
                .dropLastWhile { it.toString() == "0" }
                .toIntOrNull() ?: 0
            _timerData.value = _timerData.value.toMutableList().also {
                it[index] = it[index].copy(number = newValue)
            }
        } else {
            val newValue = if (newText == "0") 0 else newText.filter { it.isDigit() }
                .toIntOrNull() ?: 0
            _timerData.value = _timerData.value.toMutableList().also {
                it[index] = it[index].copy(number = newValue)
            }
        }
    }

    fun resetItemVibrationEffect(index: Int) {
        _timerData.value = _timerData.value.toMutableList().also {
            it[index] = it[index].copy(vibrationEffect = false)
        }
        _events.value = null
    }

    fun onItemClick(index: Int) {
        _timerData.value = _timerData.value.toMutableList().also {
            it[index] = it[index].copy(loading = true)
        }
        startItemCountdown(index = index, initialValue = _timerData.value[index].number)
    }

    @OptIn(InternalCoroutinesApi::class)
    private fun startItemCountdown(index: Int = 0, initialValue: Int) {
        job = viewModelScope.launch {
            Log.d("vib", "ration waiting begins")
            if (timerData.value[0].loading) {
                _timerData.value[index].endTime =
                    LocalDateTime.now().plusSeconds(initialValue.toLong())
                _timerData.value[0].count = initialValue
                while (
                    timerData.value[index].endTime!! > LocalDateTime.now() &&
                    _timerData.value[0].count > 0
                ) {
                    _timerData.update {
                        it.toMutableList().also { timer ->
                            timer[index] = timer[index].copy(
                                count = timer[index].endTime!!.toEpochSecond(ZoneOffset.UTC)
                                    .toInt() -
                                        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toInt(),
                            )
                        }
                    }
                    delay(1000)
                    println(_timerData.value[0])
                }
                _timerData.value = _timerData.value.toMutableList().also { timer ->
                    timer[index] = timer[index].copy(
                        count = 0,
                        vibrationEffect = false,
                        loading = false,
                    )
                }
            }
        }
        job?.invokeOnCompletion(
            onCancelling = false,
            invokeImmediately = true,
        ){
            _events.value = StartVibration
        }
    }

    fun onResetItemCountdown(index: Int = 0) {
        job?.cancel()
        _timerData.value = _timerData.value.toMutableList().also { timer ->
            timer[index] = timer[index].copy(
                count = 0,
                vibrationEffect = false,
                loading = false,
                number = 0,
            )
        }
        _events.value = null
    }
}