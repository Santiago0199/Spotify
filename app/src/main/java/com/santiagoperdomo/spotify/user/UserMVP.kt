package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.Observable

class UserMVP {

    interface View{
        fun showToast(message: String)
        fun updateUserProfile(user: User)
        fun successRequest()
    }

    interface Presenter{
        fun setView(view: View)
        fun loadUserProfile()
        fun unsuscribeUserProfile()
    }

    interface Model{
        fun getUserProfile(): Observable<User>
    }

}