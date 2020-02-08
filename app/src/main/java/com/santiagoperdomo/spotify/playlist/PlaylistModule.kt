package com.santiagoperdomo.spotify.playlist

import com.santiagoperdomo.spotify.http.SpotifyApiService
import dagger.Module
import dagger.Provides

@Module
class PlaylistModule {

    @Provides
    fun providePlaylistPresenter(model: PlaylistMVP.Model): PlaylistMVP.Presenter{
        return PlaylistPresenter(model)
    }

    @Provides
    fun providePlaylistModel(repo: PlaylistRepository): PlaylistMVP.Model{
        return PlaylistModel(repo)
    }

    @Provides
    fun providePlaylistRepository(spotifyApiService: SpotifyApiService): PlaylistRepository{
        return PlaylistRepositoryImp(spotifyApiService)
    }

}