package com.santiagoperdomo.spotify.http

import com.santiagoperdomo.spotify.http.model.ResponsePlaylists
import com.santiagoperdomo.spotify.http.model.ResponseTrack
import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface SpotifyApiService {

    @GET("me")
    fun getUserProfile(): Observable<User>

    @GET("me/playlists")
    fun getPlaylists(): Observable<ResponsePlaylists>

    @GET("playlists/{playlist_id}/tracks")
    fun getPlaylistTracks(@Path("playlist_id") playlist_id: String): Observable<ResponseTrack>

}