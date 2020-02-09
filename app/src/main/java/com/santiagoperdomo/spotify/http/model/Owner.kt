package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Owner(
    displayName: String?,
    externalUrls: ExternalUrls,
    href: String,
    id: String,
    images: List<Image>,
    type: String,
    uri: String
): Serializable {

    @SerializedName("display_name")
    @Expose
    var displayName: String?
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
    @SerializedName("type")
    @Expose
    var type: String
    @SerializedName("uri")
    @Expose
    var uri: String

    init{
        this.displayName = displayName
        this.externalUrls = externalUrls
        this.href = href
        this.id = id
        this.images = images
        this.type = type
        this.uri = uri
    }

}