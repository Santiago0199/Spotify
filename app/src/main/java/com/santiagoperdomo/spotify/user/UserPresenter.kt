package com.santiagoperdomo.spotify.user

import com.santiagoperdomo.spotify.http.model.Playlists
import com.santiagoperdomo.spotify.http.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class UserPresenter(model: UserMVP.Model): UserMVP.Presenter {

    val tag = "UserPresenter"
    val failUser = "Fallo al obtener los datos del perfil"
    val failPlaylist = "Fallo al obtener los playlists"
    val successCreatedPlaylist = "Playlist creado con exito"
    val errorCreatedPlaylist = "Playlist no se pudo crear"

    private lateinit var view: UserMVP.View
    var model: UserMVP.Model
    private var suscriptionUserProfile: Disposable? = null
    private var suscriptionPlaylists: Disposable? = null
    private var suscriptionCreatedPlaylist: Disposable? = null

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
                }
                override fun onNext(user: User) {
                    view.updateUserProfile(user)
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.showToast(failUser)
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

    override fun createdPlaylist(name: String, idUser: String) {
        suscriptionCreatedPlaylist = model.getPlaylistCreated(name, idUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith (object : DisposableObserver<Playlists>() {
                override fun onComplete() {
                    view.showToast(successCreatedPlaylist)
                }
                override fun onNext(playlists: Playlists) {
                    view.updatePlaylists(playlists)
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })

        model.getPlaylistCreated(name, idUser)
    }

    override fun validateNamePlaylist(name: String): Boolean {
        return name != ""
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

    fun unsuscribeCreatedPlaylist() {
        if(suscriptionCreatedPlaylist == null || suscriptionCreatedPlaylist!!.isDisposed){
            suscriptionCreatedPlaylist!!.dispose()
        }
    }
}