package com.bensdevelops.myGOT

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
/*
This is the base application class where all Dagger components will be generated either manually
or automatically as is the case for everything in this app
Could have some field injection or re-injection but currently no use for it.
 */
@HiltAndroidApp
class MyGOTApplication: Application () {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}