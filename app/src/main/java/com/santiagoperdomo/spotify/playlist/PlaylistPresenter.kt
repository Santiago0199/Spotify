package com.santiagoperdomo.spotify.playlist

import android.util.Log
import com.santiagoperdomo.spotify.http.model.ItemTrack
import com.santiagoperdomo.spotify.http.model.Playlists
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class PlaylistPresenter(model: PlaylistMVP.Model): PlaylistMVP.Presenter {

    val tag = "PlaylistPresenter"
    val failTracks = "Fallo al obtener los datos de la pista"
    val successUpdatePlaylist = "Playlist actualizado con exito"
    val errorUpdatePlaylist = "Playlist no se pudo actualizar"

    var model: PlaylistMVP.Model
    private lateinit var view: PlaylistMVP.View
    private var suscriptionTrack: Disposable? = null
    private var suscriptionUpdatePlaylist: Disposable? = null

    init {
        this.model = model
    }

    override fun setView(view: PlaylistMVP.View) {
        this.view = view
    }

    override fun loadTrack(id: String) {
        suscriptionTrack = model.getTrack(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith (object : DisposableObserver<ItemTrack>() {
                override fun onComplete() {
                    view.successRequest()
                }
                override fun onNext(itemTrack: ItemTrack) {
                    view.updateTrack(itemTrack)
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.showToast(failTracks)
                    view.successRequest()
                }
            })
    }

    override fun updatePlaylist(name: String, idPlaylist: String) {
        suscriptionUpdatePlaylist = model.getPlaylistUpdate(name, idPlaylist)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith (object : DisposableObserver<Playlists>() {
                override fun onComplete() {
                    view.showToast(successUpdatePlaylist)
                }
                override fun onNext(playlists: Playlists) {
                    view.updatePlaylist(playlists)
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    val error = e as HttpException
                    val errorBody = error.response()!!.errorBody()!!.string()
                    Log.e(tag, errorBody)
                }
            })
    }

    override fun validateNamePlaylist(name: String): Boolean {
        return name != ""
    }

    override fun unsuscribeTrack() {
        if(suscriptionTrack == null || suscriptionTrack!!.isDisposed){
            suscriptionTrack!!.dispose()
        }
    }

    fun unsuscribeUpdatePlaylist() {
        if(suscriptionUpdatePlaylist == null || suscriptionUpdatePlaylist!!.isDisposed){
            suscriptionUpdatePlaylist!!.dispose()
        }
    }


}