package com.santiagoperdomo.spotify.user

class UserModel(userRepository: UserRepository): UserMVP.Model{

    var userRepository: UserRepository

    init {
        this.userRepository = userRepository
    }

}