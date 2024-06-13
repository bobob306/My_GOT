package com.bensdevelops.myGOT.core.base.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bensdevelops.myGOT.ui.theme.SizeTokens

@Composable
fun GOTColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical? = null,
    horizontalAlignment:  Alignment.Horizontal? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.padding(SizeTokens.large),
        verticalArrangement = verticalArrangement ?: Arrangement.Top,
        horizontalAlignment = horizontalAlignment ?: Alignment.Start,
    ) {
        content()
    }
}