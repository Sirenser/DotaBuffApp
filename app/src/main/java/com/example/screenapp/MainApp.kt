package com.example.screenapp

import android.app.Application
import com.example.screenapp.di.AppComponent
import com.example.screenapp.di.DaggerAppComponent

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}