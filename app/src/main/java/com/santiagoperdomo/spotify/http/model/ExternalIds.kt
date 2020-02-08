package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ExternalIds (isrc: String): Serializable {

    @SerializedName("isrc")
    @Expose
    var isrc: String

    init {
        this.isrc = isrc
    }

}