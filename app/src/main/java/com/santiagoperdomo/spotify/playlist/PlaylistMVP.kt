package com.santiagoperdomo.spotify.playlist

import com.santiagoperdomo.spotify.http.model.ItemTrack
import com.santiagoperdomo.spotify.http.model.Playlists
import io.reactivex.Observable

class PlaylistMVP {

    interface View{
        fun showToast(message: String)
        fun updateTrack(itemTrack: ItemTrack)
        fun updatePlaylist(playlists: Playlists)
        fun successRequest()
        //fun responseDelete(): Boolean
    }

    interface Presenter{
        fun setView(view: View)
        fun loadTrack(id: String)
        fun unsuscribeTrack()

        fun validateNamePlaylist(name: String): Boolean
        fun updatePlaylist(name: String, idPlaylist: String)
        //fun deletePlaylist(id: String): Boolean
    }

    interface Model{
        fun getTrack(id: String): Observable<ItemTrack>
        fun getPlaylistUpdate(name: String, idPlaylist: String): Observable<Playlists>
        //fun deletePlaylist(id: String): Boolean
    }

}