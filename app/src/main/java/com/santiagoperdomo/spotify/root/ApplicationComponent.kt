package com.santiagoperdomo.spotify.root

import com.santiagoperdomo.prueba.user.UserModule
import com.santiagoperdomo.spotify.MainActivity
import com.santiagoperdomo.spotify.http.SpotifyApiModule
import com.santiagoperdomo.spotify.playlist.PlaylistModule
import com.santiagoperdomo.spotify.playlist.PlaylistsActivity
import com.santiagoperdomo.spotify.user.UserActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, UserModule::class, PlaylistModule::class, SpotifyApiModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(userActivity: UserActivity)
    fun inject(playlistsActivity: PlaylistsActivity)

}