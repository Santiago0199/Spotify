package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ItemTrack(addedAt: String, addedBy: AddedBy, isLocal: Boolean, track: Track): Serializable {

    @SerializedName("added_at")
    @Expose
    var addedAt: String
    @SerializedName("added_by")
    @Expose
    var addedBy: AddedBy
    @SerializedName("is_local")
    @Expose
    var isLocal: Boolean = false
    @SerializedName("track")
    @Expose
    var track: Track

    init {
        this.addedAt = addedAt
        this.addedBy = addedBy
        this.isLocal = isLocal
        this.track = track
    }

}