package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class UserPresenter(model: UserMVP.Model): UserMVP.Presenter {

    val failUser = "Fallo al obtener los datos del perfil"

    private lateinit var view: UserMVP.View
    var model: UserMVP.Model
    private var suscriptionUserProfile: Disposable? = null

    init {
        this.model = model
    }

    override fun setView(view: UserMVP.View) {
        this.view = view
    }

    override fun loadUserProfile() {
        suscriptionUserProfile = model.getUserProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<User>(){
                override fun onComplete() {
                    view.successRequest()
                }
                override fun onNext(user: User) {
                    view.updateUserProfile(user)
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.showToast(failUser)
                    view.successRequest()
                }
            })
    }

    override fun unsuscribeUserProfile() {
        if(suscriptionUserProfile == null || suscriptionUserProfile!!.isDisposed){
            suscriptionUserProfile!!.dispose()
        }
    }
}