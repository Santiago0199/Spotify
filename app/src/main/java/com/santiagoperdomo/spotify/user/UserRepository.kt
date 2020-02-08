package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.Observable

interface UserRepository {

    fun getUserProfileData(): Observable<User>
    fun getUserProfileFromCache(): Observable<User>
    fun getUserProfileFromNetwork(): Observable<User>

}