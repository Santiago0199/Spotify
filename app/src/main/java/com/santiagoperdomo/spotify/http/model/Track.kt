package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Track(
    album: Album,
    artists: List<Artist>,
    availableMarkets: List<String>,
    discNumber: Int,
    durationMs: Int,
    explicit: Boolean,
    externalIds: ExternalIds,
    externalUrls: ExternalUrls,
    href: String,
    id: String,
    name: String,
    popularity: Int,
    previewUrl: String,
    trackNumber: Int,
    type: String,
    uri: String
): Serializable {

    @SerializedName("album")
    @Expose
    var album: Album
    @SerializedName("artists")
    @Expose
    var artists: List<Artist>? = null
    @SerializedName("available_markets")
    @Expose
    var availableMarkets: List<String>? = null
    @SerializedName("disc_number")
    @Expose
    var discNumber: Int = 0
    @SerializedName("duration_ms")
    @Expose
    var durationMs: Int = 0
    @SerializedName("explicit")
    @Expose
    var explicit: Boolean = false
    @SerializedName("external_ids")
    @Expose
    var externalIds: ExternalIds
    @SerializedName("external_urls")
    @Expose
    var externalUrls: ExternalUrls
    @SerializedName("href")
    @Expose
    var href: String
    @SerializedName("id")
    @Expose
    var id: String
    @SerializedName("name")
    @Expose
    var name: String
    @SerializedName("popularity")
    @Expose
    var popularity: Int = 0
    @SerializedName("preview_url")
    @Expose
    var previewUrl: String
    @SerializedName("track_number")
    @Expose
    var trackNumber: Int = 0
    @SerializedName("type")
    @Expose
    var type: String
    @SerializedName("uri")
    @Expose
    var uri: String

    init {
        this.album = album
        this.artists = artists
        this.availableMarkets = availableMarkets
        this.discNumber = discNumber
        this.durationMs = durationMs
        this.explicit = explicit
        this.externalIds = externalIds
        this.externalUrls = externalUrls
        this.href = href
        this.id = id
        this.name = name
        this.popularity = popularity
        this.previewUrl = previewUrl
        this.trackNumber = trackNumber
        this.type = type
        this.uri = uri
    }
}