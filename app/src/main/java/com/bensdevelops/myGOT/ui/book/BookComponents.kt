package com.bensdevelops.myGOT.ui.book

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bensdevelops.myGOT.core.base.ui.GOTColumn
import com.bensdevelops.myGOT.core.viewData.homeScreen.BookViewData

@Composable
fun BookOverview(books: List<BookViewData>, onClick: (String, Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp),
    ) {
        item(books) {
            books.forEach { book ->
                BookOverviewBox(book = book, onClick, index = books.indexOf(book))
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
            }
        }
    }
}

@Composable
fun BookOverviewBox(book: BookViewData, onClick: (String, Int) -> Unit, index: Int) {
    book.run {
        GOTColumn(
            modifier = Modifier
                .fillMaxWidth()
                .border(color = MaterialTheme.colorScheme.primary, width = 1.dp)
                .clickable(onClick = { onClick("book", index) })

        ) {
            Text(text = "Title = $name")
            Text(text = "Author = $authors")
            Text(text = "Released = $released")
            Text(text = "isbn = $isbn")
        }
    }
}

@Composable
fun BookDetails(
    book: BookViewData
) {
    book.run {
        GOTColumn(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth(),
        ) {
            Text(text = "$name")
            authors?.let { Text(text = "Author = $it") }
            publisher?.let { Text(text = "Publisher = $it") }
            released?.let { Text(text = "Released = $it") }
            numberPages?.let { Text(text = "Pages = $it") }
            characters?.let { Text(text = "Characters = $it") }
            povCharacters?.let { Text(text = "Point of view characters = $it") }
            something?.let { Text(text = "Other details = $it") }
        }
    }
}