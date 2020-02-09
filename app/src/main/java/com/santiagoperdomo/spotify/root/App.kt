package com.santiagoperdomo.spotify.root

import android.app.Application
import com.santiagoperdomo.prueba.user.UserModule
import com.santiagoperdomo.spotify.http.SpotifyApiModule
import com.santiagoperdomo.spotify.playlist.PlaylistModule
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
            .userModule(UserModule())
            .playlistModule(PlaylistModule())
            .spotifyApiModule(SpotifyApiModule())
            .build()
    }
}