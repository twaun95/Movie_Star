package com.twaun95.moviestar.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieStarApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}