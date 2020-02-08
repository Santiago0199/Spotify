package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User(country: String,
           displayName: String,
           email: String,
           externalUrls: ExternalUrls,
           followers: Followers,
           href: String,
           id: String,
           images: List<Image>,
           product: String,
           type: String,
           uri: String) {

    @SerializedName("country")
    @Expose
    var country: String
    @SerializedName("display_name")
    @Expose
    var displayName: String
    @SerializedName("email")
    @Expose
    var email: String
    @SerializedName("external_urls")
    @Expose
    var externalUrls: ExternalUrls
    @SerializedName("followers")
    @Expose
    var followers: Followers
    @SerializedName("href")
    @Expose
    var href: String
    @SerializedName("id")
    @Expose
    var id: String
    @SerializedName("images")
    @Expose
    var images: List<Image>? = null
    @SerializedName("product")
    @Expose
    var product: String
    @SerializedName("type")
    @Expose
    var type: String
    @SerializedName("uri")
    @Expose
    var uri: String

    init {
        this.country = country
        this.displayName = displayName
        this.email = email
        this.externalUrls = externalUrls
        this.followers = followers
        this.href = href
        this.id = id
        this.images = images
        this.product = product
        this.type = type
        this.uri = uri

    }
}