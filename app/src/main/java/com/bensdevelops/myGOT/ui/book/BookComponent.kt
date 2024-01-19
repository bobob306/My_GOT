package com.bensdevelops.myGOT.ui.book

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bensdevelops.myGOT.core.viewData.homeScreen.BookViewData

@Composable
fun BookOverview(books: List<BookViewData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp),
    ) {
        item(books) {
            books.forEach { book ->
                BookOverviewBox(book = book)
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
            }
        }
    }
}

@Composable
fun BookOverviewBox(book: BookViewData) {
    book.run {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(color = MaterialTheme.colorScheme.primary, width = 1.dp)
                .padding(16.dp)
        ) {
            Text(text = "Title = $name")
            Text(text = "Author = $authors")
            Text(text = "Released = $released")
            Text(text = "isbn = $isbn")
        }
    }
}