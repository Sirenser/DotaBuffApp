package com.example.screenapp

import android.app.Application
import com.example.screenapp.di.AppComponent
import com.example.screenapp.di.DaggerAppComponent

class MainApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .build()
    }


}