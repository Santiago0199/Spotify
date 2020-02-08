package com.santiagoperdomo.spotify.playlist

import com.santiagoperdomo.spotify.http.model.ItemTrack
import io.reactivex.Observable

interface PlaylistRepository {

    fun getTrackData(id: String): Observable<ItemTrack>
    fun getTrackFromCache(): Observable<ItemTrack>
    fun getTrackNetwork(id: String): Observable<ItemTrack>

}