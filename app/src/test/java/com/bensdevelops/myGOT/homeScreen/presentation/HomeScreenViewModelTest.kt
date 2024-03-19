package com.bensdevelops.myGOT.homeScreen.presentation

import androidx.lifecycle.SavedStateHandle
import com.bensdevelops.myGOT.mapper.ViewDataMapper
import com.bensdevelops.myGOT.network.repository.Repository
import com.bensdevelops.myGOT.ui.screens.homeScreen.DataOptions
import com.bensdevelops.myGOT.ui.screens.homeScreen.HomeScreenViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class HomeScreenViewModelTest {

    private lateinit var underTest: HomeScreenViewModel

    private lateinit var mockRepository: Repository
    private lateinit var mockHandle: SavedStateHandle
    private lateinit var mockViewDataMapper: ViewDataMapper

    @Before
    fun setUp() {
        mockRepository = mock()
        mockHandle = mock()
        mockViewDataMapper = mock()
        underTest = HomeScreenViewModel(mockRepository, mockHandle, mockViewDataMapper)
    }

    @Test
    fun `when books is clicked then books are shown`() {
        underTest.onClearClick()
        val expected = null
        val actual = underTest.showData.value
        assertEquals(expected, actual)

    }
}