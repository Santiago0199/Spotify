package com.santiagoperdomo.spotify.user

class UserMVP {

    interface View{
    }

    interface Presenter{
        fun setView(view: View)
    }

    interface Model{
    }

}