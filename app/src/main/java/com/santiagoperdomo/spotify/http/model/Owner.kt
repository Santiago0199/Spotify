package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Owner(
    externalUrls: ExternalUrls,
    href: String,
    id: String,
    type: String,
    uri: String
): Serializable {

    @SerializedName("external_urls")
    @Expose
    var externalUrls: ExternalUrls
    @SerializedName("href")
    @Expose
    var href: String
    @SerializedName("id")
    @Expose
    var id: String
    @SerializedName("type")
    @Expose
    var type: String
    @SerializedName("uri")
    @Expose
    var uri: String

    init{
        this.externalUrls = externalUrls
        this.href = href
        this.id = id
        this.type = type
        this.uri = uri
    }

}