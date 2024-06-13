package com.bensdevelops.myGOT.ui.house

import androidx.compose.foundation.border
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
import com.bensdevelops.myGOT.core.base.ui.GOTColumn
import com.bensdevelops.myGOT.core.viewData.homeScreen.HouseViewData

@Composable
fun HouseOverview(houses: List<HouseViewData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp)
    ) {
        item(houses) {
            houses.forEach { house ->
                HouseOverviewBox(house = house)
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
            }
        }
    }
}

@Composable
fun HouseOverviewBox(house: HouseViewData) {
    house.run {
        GOTColumn(
            modifier = Modifier
                .fillMaxWidth()
                .border(color = MaterialTheme.colorScheme.primary, width = 1.dp)
        ) {
            Text(text = "Name = $name")
            Text(text = "Region = $region")
            Text(text = "Current Lord = $currentLord")
            Text(text = "Words = $words")
        }
    }
}