//package com.bensdevelops.myGOT.homeScreen.presentation
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.Observer
//import androidx.lifecycle.SavedStateHandle
//import com.bensdevelops.myGOT.core.base.ViewData
//import com.bensdevelops.myGOT.core.viewData.homeScreen.HomeScreenViewData
//import com.bensdevelops.myGOT.homeScreen.presentation.mapper.TestData
//import com.bensdevelops.myGOT.mapper.ViewDataMapper
//import com.bensdevelops.myGOT.network.repository.Repository
//import com.bensdevelops.myGOT.ui.screens.homeScreen.DataOptions
//import com.bensdevelops.myGOT.ui.screens.homeScreen.HomeScreenViewModel
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.verify
//import org.mockito.Mockito.`when`
//import org.mockito.junit.MockitoJUnitRunner
//import org.mockito.kotlin.doReturn
//import org.mockito.kotlin.given
//import org.mockito.kotlin.whenever
//
//@RunWith(MockitoJUnitRunner::class)
//@ExperimentalCoroutinesApi
//class HomeScreenViewModelTest {
//
//    @get:Rule
//    var rule = InstantTaskExecutorRule()
//
//    private lateinit var underTest: HomeScreenViewModel
//
//    @Mock
//    lateinit var repository: Repository
//
//    @Mock
//    lateinit var handle: SavedStateHandle
//
//    @Mock
//    lateinit var viewDataMapper: ViewDataMapper
//
//    private val viewDataObserver: Observer<ViewData<HomeScreenViewData>> = mock()
//    private val showDataObserver: Observer<DataOptions?> = mock()
//
//    @Before
//    fun setUp() {
//        underTest = HomeScreenViewModel(repository, handle, viewDataMapper)
//            .apply {
//                viewData.observeForever(viewDataObserver)
//                showData.observeForever(showDataObserver)
//            }
//    }
//
//    @Test
//    fun `when books is clicked successfully then books are shown`() = runTest {
//        mockBookSuccessResult()
//        underTest.onBooksClick()
//        val expected: DataOptions? = DataOptions.BOOKS
//        val actual = underTest.showData.value
//        assertEquals(expected, actual)
//        verify(viewDataObserver).onChanged(ViewData.Loading())
//        verify(viewDataObserver).onChanged(ViewData.Data(HomeScreenViewData(TestData.booksTestViewData)))
//    }
//
//    private suspend fun mockBookSuccessResult() {
//        given(
//            viewDataMapper.map(
//                books = TestData.booksTestData,
//                characters = null,
//                houses = null,
//            )
//        ).willReturn(HomeScreenViewData(TestData.booksTestViewData))
//        `when`(repository.getBooks()).thenReturn(Result.success(TestData.booksTestData)).apply {
//        }
//    }
//}