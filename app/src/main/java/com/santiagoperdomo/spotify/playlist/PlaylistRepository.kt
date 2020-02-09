package com.santiagoperdomo.spotify.playlist

import com.santiagoperdomo.spotify.http.model.ItemTrack
import com.santiagoperdomo.spotify.http.model.Playlists
import com.santiagoperdomo.spotify.http.model.RequestPlaylist
import io.reactivex.Observable

interface PlaylistRepository {

    fun getTrackData(id: String): Observable<ItemTrack>
    fun getTrackFromCache(): Observable<ItemTrack>
    fun getTrackNetwork(id: String): Observable<ItemTrack>

    fun getPlaylistUpdateData(requestPlaylist: RequestPlaylist, idPlaylist: String): Observable<Playlists>

    //fun deletePlaylist(id: String): Boolean
}