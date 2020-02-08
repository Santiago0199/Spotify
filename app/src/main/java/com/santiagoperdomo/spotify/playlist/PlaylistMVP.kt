package com.santiagoperdomo.spotify.playlist

import com.santiagoperdomo.spotify.http.model.ItemTrack
import io.reactivex.Observable

class PlaylistMVP {

    interface View{
        fun showToast(message: String)
        fun updateTrack(itemTrack: ItemTrack)
        fun successRequest()
    }

    interface Presenter{
        fun setView(view: View)
        fun loadTrack(id: String)
        fun unsuscribeTrack()
    }

    interface Model{
        fun getTrack(id: String): Observable<ItemTrack>
    }

}