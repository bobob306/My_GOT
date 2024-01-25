package com.bensdevelops.myGOT.core.base.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
Just for when view data is in the loading state, partly to improve the experience, and to stop a
user clicking multiple buttons, making multiple network requests and then potentially receiving the
responses to those requests in the incorrect order.
 */

@Composable
fun GenericLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(96.dp)
                .then(modifier),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 9.dp,
        )
    }
}