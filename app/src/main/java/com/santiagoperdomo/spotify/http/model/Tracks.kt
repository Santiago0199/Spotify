package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Tracks(
    href: String,
    items: List<Objects>,
    limit: Int,
    next: Any,
    offset: Int,
    previous: Any,
    total: Int
): Serializable {

    @SerializedName("href")
    @Expose
    var href: String
    @SerializedName("items")
    @Expose
    var items: List<Objects>? = null
    @SerializedName("limit")
    @Expose
    var limit: Int = 0
    @SerializedName("next")
    @Expose
    var next: Any? = null
    @SerializedName("offset")
    @Expose
    var offset: Int = 0
    @SerializedName("previous")
    @Expose
    var previous: Any? = null
    @SerializedName("total")
    @Expose
    var total: Int = 0

    init{
        this.href = href
        this.items = items
        this.limit = limit
        this.next = next
        this.offset = offset
        this.previous = previous
        this.total = total
    }

}