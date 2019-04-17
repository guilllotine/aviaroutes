package com.maxkuznetsov.aviaroutes

import android.app.Application

/**
 * Created by max
 */
class AppDelegate : Application() {

    private lateinit var component: AppComponent

    companion object {
        lateinit var app: AppDelegate
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        component = prepareAppComponent().build()
    }

    private fun prepareAppComponent(): DaggerAppComponent.Builder {
        return DaggerAppComponent
            .builder()
    }

    fun getAppComponent(): AppComponent = component
}