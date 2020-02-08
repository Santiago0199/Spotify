package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.SpotifyApiService
import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.Observable

class UserRepositoryImp(spotifyApiService: SpotifyApiService): UserRepository {

    private val spotifyApiService: SpotifyApiService

    private var lastTimesTamp: Long
    private val CACHE_LIFETIME: Long
    private var userProfile: User?

    init {
        this.spotifyApiService = spotifyApiService
        lastTimesTamp = System.currentTimeMillis()
        CACHE_LIFETIME = 20 * 1000
        userProfile = null
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

    private fun isUpdated(): Boolean{
        return (System.currentTimeMillis() - lastTimesTamp) < CACHE_LIFETIME
    }
}