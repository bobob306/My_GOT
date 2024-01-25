package com.bensdevelops.myGOT.core.base

/*
This class handles the state of view data
A view model will set a view data as loading when it is being modified, for example added to
by a network request

When the modification is completed the data will move to the data type with a content or error if
there was some problem particularly with a network request and there is no cached data.

A bit like the Result class for network requests in a way, just a wrapper to make things a bit more
reliable and handle errors a bit more comfortably.
 */
sealed class ViewData<T> {
    data class Loading<T>(val isLoading: Boolean = true) : ViewData<T>()

    data class Data<T>(val content: T) : ViewData<T>()

    data class Error<T>(val throwable: Throwable) : ViewData<T>()
}