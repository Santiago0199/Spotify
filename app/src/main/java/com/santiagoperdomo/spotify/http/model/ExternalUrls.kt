package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ExternalUrls(spotify: String): Serializable {

    @SerializedName("spotify")
    @Expose
    var spotify: String

    init {
        this.spotify = spotify
    }


}