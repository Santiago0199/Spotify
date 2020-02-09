package com.santiagoperdomo.spotify.playlist

import com.santiagoperdomo.spotify.http.model.ItemTrack
import com.santiagoperdomo.spotify.http.model.Playlists
import com.santiagoperdomo.spotify.http.model.RequestPlaylist
import io.reactivex.Observable

class PlaylistModel(repo: PlaylistRepository): PlaylistMVP.Model {

    var repo: PlaylistRepository

    init {
        this.repo = repo
    }

    override fun getTrack(id: String): Observable<ItemTrack> {
        return repo.getTrackData(id)
    }

    override fun getPlaylistUpdate(name: String, idPlaylist: String): Observable<Playlists> {
        val requestPlaylist = RequestPlaylist(name)
        return repo.getPlaylistUpdateData(requestPlaylist, idPlaylist)
    }

}