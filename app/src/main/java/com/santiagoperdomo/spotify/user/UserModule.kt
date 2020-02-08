package com.santiagoperdomo.prueba.user

import com.santiagoperdomo.spotify.http.SpotifyApiService
import com.santiagoperdomo.spotify.user.*
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun provideUserPresenter(model: UserMVP.Model): UserMVP.Presenter{
        return UserPresenter(model)
    }

    @Provides
    fun provideUserModel(repo: UserRepository): UserMVP.Model{
        return UserModel(repo)
    }

    @Provides
    fun provideUserRepository(spotifyApiService: SpotifyApiService): UserRepository{
        return UserRepositoryImp(spotifyApiService)
    }
}