package com.bensdevelops.myGOT.navigation

sealed class Screen(
    val route: String,
    val arguments: String? = null
) {
    object HomeScreen : Screen("home_screen")

    object TimerScreen : Screen("timer_screen")

    object ErrorScreen : Screen("error_route")

    object DetailsScreen: Screen(route = "detailsScreen", arguments = null)

    object FlashCardScreen: Screen(route = "flashCardScreen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}