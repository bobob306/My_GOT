package com.bensdevelops.myGOT.homeScreen.presentation.mapper

import com.bensdevelops.myGOT.core.viewData.homeScreen.HomeScreenViewData
import com.bensdevelops.myGOT.homeScreen.presentation.mapper.TestData.booksTestData
import com.bensdevelops.myGOT.homeScreen.presentation.mapper.TestData.booksTestViewData
import com.bensdevelops.myGOT.homeScreen.presentation.mapper.TestData.characterTestData
import com.bensdevelops.myGOT.homeScreen.presentation.mapper.TestData.characterTestViewData
import com.bensdevelops.myGOT.homeScreen.presentation.mapper.TestData.housesTestData
import com.bensdevelops.myGOT.homeScreen.presentation.mapper.TestData.housesTestViewData
import com.bensdevelops.myGOT.mapper.ViewDataMapper
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ViewDataMapperTest {

    private lateinit var underTest: ViewDataMapper

    @Before
    fun setUp() {
        underTest = ViewDataMapper()
    }

    @Test
    fun `when no cached data and no data received will return null viewData`() {
        val actual = underTest.map(books = null, characters = null, houses = null)
        val expected = HomeScreenViewData(
            bookViewData = null,
            houseViewData = null,
            characterViewData = null
        )
        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `when no cached data will return bookViewData`() {
        val actual = underTest.map(books = booksTestData, characters = null, houses = null)
        val expected = HomeScreenViewData(
            bookViewData = booksTestViewData,
            houseViewData = null,
            characterViewData = null
        )
        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `when no cached data will return characterViewData`() {
        val actual = underTest.map(books = null, characters = characterTestData, houses = null)
        val expected = HomeScreenViewData(
            bookViewData = null,
            characterViewData = characterTestViewData,
            houseViewData = null,
        )
        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `when no cached data will return housesViewData`() {
        val actual = underTest.map(books = null, characters = null, houses = housesTestData)
        val expected = HomeScreenViewData(
            bookViewData = null,
            characterViewData = null,
            houseViewData = housesTestViewData,
        )
        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `when all data is passed then return all viewData`() {
        val actual = underTest.map(
            books = booksTestData,
            characters = characterTestData,
            houses = housesTestData
        )
        val expected = HomeScreenViewData(
            bookViewData = booksTestViewData,
            houseViewData = housesTestViewData,
            characterViewData = characterTestViewData
        )

        assertEquals(expected = expected, actual = actual)
    }

    private fun <T> assertEquals(expected: T, actual: T) {
        assertThat(actual).isEqualTo(expected)
    }
}