package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.model.Playlists
import com.santiagoperdomo.spotify.http.model.RequestPlaylist
import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.Observable

interface UserRepository {

    fun getUserProfileData(): Observable<User>
    fun getUserProfileFromCache(): Observable<User>
    fun getUserProfileFromNetwork(): Observable<User>

    fun getPlaylistsData(): Observable<Playlists>
    fun getPlaylistsFromCache(): Observable<Playlists>
    fun getPlaylistsFromNetwork(): Observable<Playlists>

    fun getPlaylistCreatedData(requestPlaylist: RequestPlaylist, idUser: String): Observable<Playlists>
}