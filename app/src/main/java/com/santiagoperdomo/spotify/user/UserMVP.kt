package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.model.Playlists
import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.Observable

class UserMVP {

    interface View{
        fun showToast(message: String)
        fun updateUserProfile(user: User)
        fun updatePlaylists(itemPlaylists: Playlists)
        fun successRequest()
    }

    interface Presenter{
        fun setView(view: View)
        fun loadUserProfile()
        fun unsuscribeUserProfile()

        fun loadPlaylists()
        fun unsuscribePlaylists()
    }

    interface Model{
        fun getUserProfile(): Observable<User>
        fun getPlaylists(): Observable<Playlists>
    }

}