package com.santiagoperdomo.spotify.http.model

import android.content.ClipData.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseTrack(
    href: String,
    items: List<ItemTrack>,
    limit: Int,
    next: Any,
    offset: Int,
    previous: Any,
    total: Int
) {

    @SerializedName("href")
    @Expose
    var href: String
    @SerializedName("items")
    @Expose
    var items: List<ItemTrack>? = null
    @SerializedName("limit")
    @Expose
    var limit: Int = 0
    @SerializedName("next")
    @Expose
    var next: Any
    @SerializedName("offset")
    @Expose
    var offset: Int = 0
    @SerializedName("previous")
    @Expose
    var previous: Any
    @SerializedName("total")
    @Expose
    var total: Int = 0

    init {
        this.href = href
        this.items = items
        this.limit = limit
        this.next = next
        this.offset = offset
        this.previous = previous
        this.total = total
    }

}