package com.bensdevelops.myGOT.ui.screens.detailsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bensdevelops.myGOT.core.base.ViewData
import com.bensdevelops.myGOT.core.viewData.homeScreen.CoreViewData
import com.bensdevelops.myGOT.mapper.ViewDataMapper
import com.bensdevelops.myGOT.network.model.CoreModel
import com.bensdevelops.myGOT.network.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val viewDataMapper: ViewDataMapper,
    private val handle: SavedStateHandle,
    private val repository: Repository,
) : ViewModel() {

    val something = handle.get<String>("dataType")
    val somethingElse = (handle.get<Int>("index"))?.plus(1) ?: 8
    val nice = "nice"

    private var _viewData = MutableLiveData<ViewData<CoreViewData>>()
    val viewData: LiveData<ViewData<CoreViewData>> get() = _viewData

    fun onStart() {
        _viewData.postValue(ViewData.Loading())
        viewModelScope.launch {
             when (something) {
                "book" -> {
                    repository.getBook(somethingElse.toString()).onSuccess {
                        updateViewData(it)
                    }
                }

                "house" -> {
                    repository.getHouse(somethingElse.toString()).onSuccess {
                        updateViewData(it)
                    }
                }

                "character" -> {
                    repository.getCharacter(somethingElse.toString()).onSuccess {
                        updateViewData(it)
                    }
                }
            }
        }
    }

    private fun updateViewData(model: CoreModel) {
        val mappedData = viewDataMapper.mapSingleItem(model)
        _viewData.postValue(ViewData.Data(mappedData))
    }
}