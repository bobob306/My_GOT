package com.bensdevelops.myGOT.ui.timer

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bensdevelops.myGOT.ui.theme.SizeTokens

@Composable
fun timeInput(
    labelText: String,
    value: String,
    handleItemValueChange: (String) -> Unit,
) : String {
    OutlinedTextField(
        label = { Text(text = labelText) },
        value = value,
        onValueChange = { handleItemValueChange.invoke(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.padding(bottom = SizeTokens.medium),
    )

    return value
}

