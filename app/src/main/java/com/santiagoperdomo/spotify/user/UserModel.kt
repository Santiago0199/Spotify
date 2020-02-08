package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.Observable

class UserModel(userRepository: UserRepository): UserMVP.Model{

    var userRepository: UserRepository

    init {
        this.userRepository = userRepository
    }

    override fun getUserProfile(): Observable<User> {
        return userRepository.getUserProfileData()
    }
}