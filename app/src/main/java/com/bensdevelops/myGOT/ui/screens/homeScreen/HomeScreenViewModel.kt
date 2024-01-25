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
    private val viewwDataMapper: ViewDataMapper,
) : ViewModel() {

    private var _viewData = MutableLiveData<ViewData<HomeScreenViewData>>()
    val viewData: LiveData<ViewData<HomeScreenViewData>> get() = _viewData

    private var _showData = MutableLiveData<DataOptions?>()
    val showData: LiveData<DataOptions?> get() = _showData

    fun onBooksClick() {
        Log.d("books", "started")
        _viewData.value = ViewData.Loading()
        viewModelScope.launch {
            repository.getBooks().onSuccess {
                val vm = viewwDataMapper.map(it, null, null)
                _viewData.postValue(ViewData.Data(vm))
                Log.d("chracter 2 name", vm.characterViewData?.get(1)?.name ?: "null")
                Log.d("last book name", vm.bookViewData?.last()?.name ?: "null")
                Log.d("last house name", vm.houseViewData?.last()?.name ?: "null")
                _showData.postValue(BOOKS)
            }
                .onFailure { _viewData.postValue(ViewData.Error(it)) }
        }
    }

    fun onHousesClick() {
        Log.d("houses", "started")
        _viewData.value = ViewData.Loading()
        viewModelScope.launch {
            repository.getHouses().onSuccess {
                val vm = viewwDataMapper.map(null, null, it)
                _viewData.postValue(ViewData.Data(vm))
                Log.d("chracter 2 name", vm.characterViewData?.get(1)?.name ?: "null")
                Log.d("last book name", vm.bookViewData?.last()?.name ?: "null")
                Log.d("last house name", vm.houseViewData?.last()?.name ?: "null")
                _showData.postValue(HOUSES)
            }
                .onFailure { _viewData.postValue(ViewData.Error(it)) }
        }
    }

    fun onCharactersClick() {
        Log.d("characters", "started")
        _viewData.value = ViewData.Loading()
        viewModelScope.launch {
            repository.getCharacters().onSuccess {
                val vm = viewwDataMapper.map(null, it, null)
                _viewData.postValue(ViewData.Data(vm))
                Log.d("chracter 2 name", vm.characterViewData?.get(1)?.name ?: "null")
                Log.d("last book name", vm.bookViewData?.last()?.name ?: "null")
                Log.d("last house name", vm.houseViewData?.last()?.name ?: "null")
                _showData.postValue(CHARACTERS)
            }
                .onFailure { _viewData.postValue(ViewData.Error(it)) }
        }
    }

    fun onClearClick() {
        _showData.postValue(null)
    }

    fun onNavigateToDummyScreen() {

    }
}