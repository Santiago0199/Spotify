package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Album(
    albumType: String,
    artists: List<Artist>,
    availableMarkets: List<String>,
    externalUrls: ExternalUrls,
    href: String,
    id: String,
    images: List<Image>,
    name: String,
    type: String,
    uri: String
): Serializable {

    @SerializedName("album_type")
    @Expose
    var albumType: String
    @SerializedName("artists")
    @Expose
    var artists: List<Artist>? = null
    @SerializedName("available_markets")
    @Expose
    var availableMarkets: List<String>? = null
    @SerializedName("external_urls")
    @Expose
    var externalUrls: ExternalUrls
    @SerializedName("href")
    @Expose
    var href: String
    @SerializedName("id")
    @Expose
    var id: String
    @SerializedName("images")
    @Expose
    var images: List<Image>? = null
    @SerializedName("name")
    @Expose
    var name: String
    @SerializedName("type")
    @Expose
    var type: String
    @SerializedName("uri")
    @Expose
    var uri: String

    init {
        this.albumType = albumType
        this.artists = artists
        this.availableMarkets = availableMarkets
        this.externalUrls = externalUrls
        this.href = href
        this.id = id
        this.images = images
        this.name = name
        this.type = type
        this.uri = uri
    }

}