package com.santiagoperdomo.spotify.playlist

import com.santiagoperdomo.spotify.http.SpotifyApiService
import com.santiagoperdomo.spotify.http.model.ItemTrack
import io.reactivex.Observable

class PlaylistRepositoryImp(spotifyApiService: SpotifyApiService): PlaylistRepository {

    private val spotifyApiService: SpotifyApiService
    private var lastTimesTamp: Long
    private val CACHE_LIFETIME: Long
    private var listTracks: ArrayList<ItemTrack>

    init {
        this.spotifyApiService = spotifyApiService
        lastTimesTamp = System.currentTimeMillis()
        CACHE_LIFETIME = 20 * 1000
        listTracks = ArrayList()
    }

    override fun getTrackData(id: String): Observable<ItemTrack> {
        return getTrackFromCache().switchIfEmpty(getTrackNetwork(id))
    }

    override fun getTrackFromCache(): Observable<ItemTrack> {
        return if(isUpdated()){
            Observable.fromIterable(listTracks)
        }else{
            lastTimesTamp = System.currentTimeMillis()
            listTracks.clear()
            Observable.empty()
        }
    }

    override fun getTrackNetwork(id: String): Observable<ItemTrack> {
        return spotifyApiService.getPlaylistTracks(id).concatMap { responseTrack ->
            Observable.fromIterable(responseTrack.items)
        }.doOnNext { item ->
            listTracks.add(item)
        }
    }

    private fun isUpdated(): Boolean{
        return (System.currentTimeMillis() - lastTimesTamp) < CACHE_LIFETIME
    }

}