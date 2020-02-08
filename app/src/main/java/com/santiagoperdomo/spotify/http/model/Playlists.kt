package com.santiagoperdomo.spotify.http.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Playlists(
    collaborative: Boolean,
    description: Any,
    externalUrls: ExternalUrls,
    followers: Followers,
    href: String,
    id: String,
    images: List<Image>,
    name: String,
    owner: Owner,
    public: Boolean,
    snapshotId: String,
    tracks: Tracks,
    type: String,
    uri: String
): Serializable {

    @SerializedName("collaborative")
    @Expose
    var collaborative: Boolean = false
    @SerializedName("description")
    @Expose
    var description: Any? = null
    @SerializedName("external_urls")
    @Expose
    var externalUrls: ExternalUrls
    @SerializedName("followers")
    @Expose
    var followers: Followers? = null
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
    @SerializedName("owner")
    @Expose
    var owner: Owner
    @SerializedName("public")
    @Expose
    var public: Boolean = false
    @SerializedName("snapshot_id")
    @Expose
    var snapshotId: String
    @SerializedName("tracks")
    @Expose
    var tracks: Tracks
    @SerializedName("type")
    @Expose
    var type: String
    @SerializedName("uri")
    @Expose
    var uri: String

    init {
        this.collaborative = collaborative
        this.description = description
        this.externalUrls = externalUrls
        this.followers = followers
        this.href = href
        this.id = id
        this.images = images
        this.name = name
        this.owner = owner
        this.public = public
        this.snapshotId = snapshotId
        this.tracks = tracks
        this.type = type
        this.uri = uri
    }
}