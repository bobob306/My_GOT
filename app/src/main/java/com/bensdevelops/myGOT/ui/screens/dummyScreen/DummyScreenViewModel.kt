package com.bensdevelops.myGOT.ui.screens.dummyScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bensdevelops.myGOT.core.base.ViewData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import javax.inject.Inject

class DummyScreenViewModel @Inject constructor() : ViewModel() {
    private var _number = MutableStateFlow(0)
    val number = _number.asStateFlow()

    fun handleValueChange(newText: String) {
        val newValue = if (newText == "0") 0 else newText.filter { it.isDigit() }.toIntOrNull() ?: 0
        _number.value = newValue
    }

    fun onClick() {
        Log.d("vib", "ration waiting begins")
        sleep(number.value.times(1000).toLong())
        Log.d("vib", "ration waiting ends")
    }
}