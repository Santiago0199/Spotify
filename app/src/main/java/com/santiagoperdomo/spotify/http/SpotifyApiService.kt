package com.santiagoperdomo.spotify.http

import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface SpotifyApiService {

    @GET("me")
    fun getUserProfile(): Observable<User>

}