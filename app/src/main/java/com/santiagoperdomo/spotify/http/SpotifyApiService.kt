package com.santiagoperdomo.spotify.http

import com.santiagoperdomo.spotify.http.model.*
import io.reactivex.Observable
import retrofit2.http.*

interface SpotifyApiService {

    @GET("me")
    fun getUserProfile(): Observable<User>

    @GET("me/playlists")
    fun getPlaylists(): Observable<ResponsePlaylists>

    @GET("playlists/{playlist_id}/tracks")
    fun getPlaylistTracks(@Path("playlist_id") playlist_id: String): Observable<ResponseTrack>

    @POST("users/{user_id}/playlists")
    fun createPlaylist(@Path("user_id") user_id: String, @Body requestPlaylist: RequestPlaylist): Observable<Playlists>

    @PUT("playlists/{playlist_id}")
    fun updatePlaylist(@Path("playlist_id") playlist_id: String, @Body requestPlaylist: RequestPlaylist): Observable<Playlists>

}