package com.santiagoperdomo.spotify.playlist

import com.santiagoperdomo.spotify.http.model.ItemTrack
import io.reactivex.Observable

class PlaylistModel(repo: PlaylistRepository): PlaylistMVP.Model {

    var repo: PlaylistRepository

    init {
        this.repo = repo
    }

    override fun getTrack(id: String): Observable<ItemTrack> {
        return repo.getTrackData(id)
    }

}