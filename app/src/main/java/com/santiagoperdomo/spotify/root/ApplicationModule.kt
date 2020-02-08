package com.santiagoperdomo.spotify.root

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {

    @Singleton @Provides fun provideContext() = application
}