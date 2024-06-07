package com.bensdevelops.myGOT.ui.screens.homeScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bensdevelops.myGOT.core.base.ViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.HomeScreenViewData
import com.bensdevelops.myGOT.mapper.ViewDataMapper
import com.bensdevelops.myGOT.network.repository.Repository
import com.bensdevelops.myGOT.ui.screens.homeScreen.DataOptions.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class DataOptions{
    BOOKS,
    HOUSES,
    CHARACTERS,
}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: Repository,
    private val handle: SavedStateHandle,
    private val viewDataMapper: ViewDataMapper,
) : ViewModel() {

    private var _viewData = MutableLiveData<ViewData<HomeScreenViewData>>()
    val viewData: LiveData<ViewData<HomeScreenViewData>> get() = _viewData

    fun onBooksClick() {
        _viewData.value = ViewData.Loading()
        viewModelScope.launch {
            repository.getBooks().onSuccess {
                val vm = viewDataMapper.map(it, null, null)
                _viewData.postValue(ViewData.Data(content = vm.copy(showData = BOOKS)))
            }
                .onFailure { _viewData.postValue(ViewData.Error(it)) }
        }
    }

    fun onHousesClick() {
        _viewData.value = ViewData.Loading()
        viewModelScope.launch {
            repository.getHouses().onSuccess {
                val vm = viewDataMapper.map(null, null, it)
                _viewData.postValue(ViewData.Data(vm.copy(showData = HOUSES)))
            }
                .onFailure { _viewData.postValue(ViewData.Error(it)) }
        }
    }

    fun onCharactersClick() {
        _viewData.value = ViewData.Loading()
        viewModelScope.launch {
            repository.getCharacters().onSuccess {
                val vm = viewDataMapper.map(null, it, null)
                _viewData.postValue(ViewData.Data(vm.copy(showData = CHARACTERS)))
            }
                .onFailure { _viewData.postValue(ViewData.Error(it)) }
        }
    }

    fun onClearClick() {
        _viewData.value = ViewData.Loading()
        val vm = viewDataMapper.clear()
        _viewData.postValue(ViewData.Data(vm))
    }

    // on navigate away avoid the error state being stored in memory
    fun reset() {
        _viewData.postValue(ViewData.Data(HomeScreenViewData()))
    }

    fun onNavigateToDummyScreen() {
        Log.d("navigation to dummy screen", "started")
    }
}