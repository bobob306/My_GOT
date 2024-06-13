package com.bensdevelops.myGOT.ui.screens.dummyScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DummyScreenViewModel @Inject constructor() : ViewModel() {
    private var _number = MutableStateFlow(0)
    val number = _number.asStateFlow()

    private var _viv = MutableStateFlow(false)
    val viv = _viv.asStateFlow()

    private var _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private var currentJob: Job? = null

    fun handleValueChange(newText: String) {
        val newValue = if (newText == "0") 0 else newText.filter { it.isDigit() }.toIntOrNull() ?: 0
        _number.value = newValue
    }

    fun resetViv() {
        _viv.value = false
    }

    suspend fun onClick() {
        _loading.value = true
        Log.d("vib", "ration waiting begins")
        countDown()
    }

    private suspend fun countDown(cancel: Boolean? = false) {
        currentJob = viewModelScope.launch {
                delay(number.value.times(1000).toLong())
                Log.d("vib", "ration waiting ends")
                _viv.value = true
                _loading.value = false
        }
    }

    fun onClearClick() {
        _number.value = 0
        currentJob?.cancel()
        _viv.value = false
        _loading.value = false
    }
}