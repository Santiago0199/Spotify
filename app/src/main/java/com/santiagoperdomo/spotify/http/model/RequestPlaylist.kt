package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestPlaylist(name: String) {

    @SerializedName("name")
    @Expose
    var name: String

    init {
        this.name = name
    }

}