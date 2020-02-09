package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Followers(href: String?, total: Int): Serializable {

    @SerializedName("href")
    @Expose
    var href: String?
    @SerializedName("total")
    @Expose
    var total: Int = 0

    init {
        this.href = href
        this.total = total
    }

}