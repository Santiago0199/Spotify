package com.santiagoperdomo.spotify.user

class UserPresenter(model: UserMVP.Model): UserMVP.Presenter {

    private lateinit var view: UserMVP.View
    var model: UserMVP.Model
    init {
        this.model = model
    }

    override fun setView(view: UserMVP.View) {
        this.view = view
    }
}