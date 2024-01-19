package com.bensdevelops.myGOT.navigation

sealed class Screen(
    val route: String,
    val arguments: String? = null
) {
    object HomeScreen : Screen("home_screen")

    object DummyScreen : Screen("dummy_route")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}