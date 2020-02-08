package com.santiagoperdomo.spotify.root

import com.santiagoperdomo.prueba.user.UserModule
import com.santiagoperdomo.spotify.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, UserModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

}