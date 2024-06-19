package com.bensdevelops.myGOT.ui.screens.dummyScreen

import android.util.Log
import androidx.collection.MutableIntList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class TimerScreenViewModel @Inject constructor() : ViewModel() {
    private var _number = MutableStateFlow(0)
    val number = _number.asStateFlow()

    private var _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    private var _vibrationEffect = MutableStateFlow(false)
    val vibrationEffect = _vibrationEffect.asStateFlow()

    private var _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private var endTime: LocalDateTime? = null

    fun handleValueChange(newText: String) {
        val newValue = if (newText == "0") 0 else newText.filter { it.isDigit() }
            .toIntOrNull() ?: 0
        _number.value = newValue
    }

    fun resetVibrationEffect() {
        _vibrationEffect.value = false
    }

    fun onClick() {
        _loading.value = true
        Log.d("vib", "ration waiting begins")
        startCountdown(initialValue = number.value)
    }

    private fun startCountdown(initialValue: Int) {
        viewModelScope.launch {
            endTime = LocalDateTime.now().plusSeconds(initialValue.toLong())
            _count.value = initialValue
            while (endTime!! > LocalDateTime.now() && _count.value > 0) {
                _count.value = endTime!!.toEpochSecond(ZoneOffset.UTC).toInt() -
                        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toInt()
                delay(100) // Update every100ms for smoother countdown
            }
            if (_count.value > 0) {
                _count.value = 0
            }
            _vibrationEffect.value = true
            _loading.value = false

        }
    }

    fun resetCountdown() {
        _count.value = 0
        _vibrationEffect.value = false
        _loading.value = false
    }

}