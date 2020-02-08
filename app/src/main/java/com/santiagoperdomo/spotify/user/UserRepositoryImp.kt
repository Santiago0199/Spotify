package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.SpotifyApiService

class UserRepositoryImp(spotifyApiService: SpotifyApiService): UserRepository {

    private val spotifyApiService: SpotifyApiService

    init {
        this.spotifyApiService = spotifyApiService
    }
}