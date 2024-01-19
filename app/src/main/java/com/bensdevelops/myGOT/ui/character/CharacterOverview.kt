package com.bensdevelops.myGOT.ui.character

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
import com.bensdevelops.myGOT.core.viewData.homeScreen.CharacterViewData

@Composable
fun CharacterOverview(characters: List<CharacterViewData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp)
    ) {
        item(characters) {
            characters.forEach { character ->
                CharacterOverviewBox(character = character)
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
            }
        }
    }
}

@Composable
fun CharacterOverviewBox(character: CharacterViewData) {
    character.run {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(color = MaterialTheme.colorScheme.primary, width = 1.dp)
                .padding(16.dp)
        ) {
            Text(text = "Name = $name")
            Text(text = "Played by = $playedBy")
            Text(text = "Titles = $title")
            Text(text = "Aliases = $aliases")
        }
    }
}
