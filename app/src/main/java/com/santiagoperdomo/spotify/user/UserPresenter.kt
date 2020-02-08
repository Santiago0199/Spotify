package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.model.Playlists
import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class UserPresenter(model: UserMVP.Model): UserMVP.Presenter {

    val failUser = "Fallo al obtener los datos del perfil"
    val failPlaylist = "Fallo al obtener los playlists"

    private lateinit var view: UserMVP.View
    var model: UserMVP.Model
    private var suscriptionUserProfile: Disposable? = null
    private var suscriptionPlaylists: Disposable? = null

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

    override fun loadPlaylists() {
        suscriptionPlaylists = model.getPlaylists()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Playlists>(){
                override fun onComplete() {
                    view.successRequest()
                }
                override fun onNext(listPlaylists: Playlists) {
                    view.updatePlaylists(listPlaylists)
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.showToast(failPlaylist)
                    view.successRequest()
                }
            })
    }

    override fun unsuscribeUserProfile() {
        if(suscriptionUserProfile == null || suscriptionUserProfile!!.isDisposed){
            suscriptionUserProfile!!.dispose()
        }
    }

    override fun unsuscribePlaylists() {
        if(suscriptionPlaylists == null || suscriptionPlaylists!!.isDisposed){
            suscriptionPlaylists!!.dispose()
        }
    }
}