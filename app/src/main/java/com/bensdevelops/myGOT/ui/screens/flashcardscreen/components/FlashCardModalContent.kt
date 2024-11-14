package com.bensdevelops.myGOT.ui.screens.flashcardscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata.FlashCardScreenViewData

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlashCardModalContent(
    viewData: FlashCardScreenViewData,
    onItemClick: (String) -> Unit,
    onHideButtonClick: () -> Unit,
) {
    Surface(
        Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            Modifier
                .verticalScroll(state = rememberScrollState())
                .background(MaterialTheme.colorScheme.surface)
        ) {
            FlowRow {
                viewData.tags?.forEach {
                    Button(
                        colors = ButtonColors(
                            containerColor = if (viewData.selectedTags?.contains(it) == true)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.inversePrimary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContentColor = Color.Unspecified,
                            disabledContainerColor = Color.Unspecified,
                        ),
                        modifier = Modifier.padding(4.dp),
                        onClick = {
                            onItemClick(it)
                        }
                    ) {
                        Text(text = it)
                    }
                }
            }
            // Sheet content
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    onHideButtonClick()
                }
            ) {
                Text("Hide bottom sheet")
            }
        }
    }
}