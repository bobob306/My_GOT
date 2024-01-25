package com.bensdevelops.myGOT

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bensdevelops.myGOT.navigation.Navigation
import com.bensdevelops.myGOT.ui.theme.MyGOTTheme
import dagger.hilt.android.AndroidEntryPoint
/*
This will be the base class for all the generated Dagger components

Theme is called so that everything then uses the defined theme, here just the default theme
Then Navigation is called, inside navigation there is a defined first screen and all further
screens are navigated to from one another using NavController which is set in Navigation.kt

Each Screen is a member of the sealed class Screen and has at least a route and optional navigation
arguments
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyGOTTheme {
                Navigation()
            }
        }
    }
}

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MyGOTTheme {
            Greeting("Android")
        }
    }