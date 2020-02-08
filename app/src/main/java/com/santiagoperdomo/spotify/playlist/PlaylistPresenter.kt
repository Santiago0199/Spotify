package com.santiagoperdomo.spotify.playlist

import com.santiagoperdomo.spotify.http.model.ItemTrack
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class PlaylistPresenter(model: PlaylistMVP.Model): PlaylistMVP.Presenter {

    val failTracks = "Fallo al obtener los datos de la pista"

    var model: PlaylistMVP.Model
    private lateinit var view: PlaylistMVP.View
    private var suscriptionTrack: Disposable? = null

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

    override fun unsuscribeTrack() {
        if(suscriptionTrack == null || suscriptionTrack!!.isDisposed){
            suscriptionTrack!!.dispose()
        }
    }

}