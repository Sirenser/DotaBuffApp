package com.example.screenapp.di

import androidx.fragment.app.Fragment
import com.example.screenapp.MainActivity
import com.example.screenapp.MainApp
import com.example.screenapp.screens.LoginFragment
import com.example.screenapp.screens.MainFragment
import com.example.screenapp.screens.SplashFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(splashFragment: SplashFragment)

    fun inject(mainFragment: MainFragment)

    fun inject(loginFragment: LoginFragment)

    fun inject(mainActivity: MainActivity)
}

fun Fragment.getAppComponent(): AppComponent =
    (requireContext().applicationContext as MainApp).appComponent