package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.model.Playlists
import com.santiagoperdomo.spotify.http.model.RequestPlaylist
import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.Observable

class UserModel(userRepository: UserRepository): UserMVP.Model{

    var userRepository: UserRepository

    init {
        this.userRepository = userRepository
    }

    override fun getUserProfile(): Observable<User> {
        return userRepository.getUserProfileData()
    }

    override fun getPlaylists(): Observable<Playlists> {
        return userRepository.getPlaylistsData()
    }

    override fun getPlaylistCreated(name: String, idUser: String): Observable<Playlists> {
        val requestPlaylist = RequestPlaylist(name)
        return userRepository.getPlaylistCreatedData(requestPlaylist, idUser)
    }
}