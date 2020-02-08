package com.santiagoperdomo.spotify.root

import android.app.Application
import io.reactivex.plugins.RxJavaPlugins

class App: Application() {

    lateinit var component: ApplicationComponent

    companion object{
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        RxJavaPlugins.setErrorHandler { }
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}