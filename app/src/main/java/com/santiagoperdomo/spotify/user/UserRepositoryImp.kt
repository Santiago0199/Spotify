package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.SpotifyApiService
import com.santiagoperdomo.spotify.http.model.Playlists
import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.Observable

class UserRepositoryImp(spotifyApiService: SpotifyApiService): UserRepository {

    private val spotifyApiService: SpotifyApiService

    private var lastTimesTamp: Long
    private val CACHE_LIFETIME: Long
    private var userProfile: User?
    private var listPlaylists: ArrayList<Playlists>

    init {
        this.spotifyApiService = spotifyApiService
        lastTimesTamp = System.currentTimeMillis()
        CACHE_LIFETIME = 20 * 1000
        userProfile = null
        listPlaylists = ArrayList()
    }

    override fun getUserProfileData(): Observable<User> {
        if(userProfile == null){
            return getUserProfileFromNetwork()
        }
        return getUserProfileFromCache()
    }

    override fun getUserProfileFromCache(): Observable<User> {
        return if (isUpdated() && userProfile != null){
            Observable.just(userProfile)
        }else{
            lastTimesTamp = System.currentTimeMillis()
            userProfile = null
            Observable.empty()
        }
    }

    override fun getUserProfileFromNetwork(): Observable<User> {
        return spotifyApiService.getUserProfile().concatMap { userProfile ->
            Observable.just(userProfile)
        }.doOnNext { user ->
            userProfile = user
        }
    }

    override fun getPlaylistsData(): Observable<Playlists> {
        return getPlaylistsFromCache().switchIfEmpty(getPlaylistsFromNetwork())
    }

    override fun getPlaylistsFromCache(): Observable<Playlists> {
        return if(isUpdated()){
            Observable.fromIterable(listPlaylists)
        }else{
            lastTimesTamp = System.currentTimeMillis()
            listPlaylists.clear()
            Observable.empty()
        }
    }

    override fun getPlaylistsFromNetwork(): Observable<Playlists> {
        return spotifyApiService.getPlaylists().concatMap { responsePlaylists ->
            Observable.fromIterable(responsePlaylists.items)
        }.doOnNext { playlist ->
            listPlaylists.add(playlist)
        }
    }


    private fun isUpdated(): Boolean{
        return (System.currentTimeMillis() - lastTimesTamp) < CACHE_LIFETIME
    }
}