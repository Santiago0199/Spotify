package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image(height: Any, url: String, width: Int): Serializable {

    @SerializedName("height")
    @Expose
    var height: Any

    @SerializedName("url")
    @Expose
    var url: String

    @SerializedName("width")
    @Expose
    var width: Int

    init {
        this.height = height
        this.url = url
        this.width = width
    }

}