package com.santiagoperdomo.spotify.track

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector.ConnectionListener
import android.util.Log
import com.santiagoperdomo.spotify.R
import com.santiagoperdomo.spotify.common.Constants
import com.santiagoperdomo.spotify.http.model.ItemTrack

class TrackActivity : AppCompatActivity() {

    private val tag = "TrackActivity"
    private val textBy = " by "
    private val positionListImage = 0

    private var mSpotifyAppRemote: SpotifyAppRemote? = null

    private lateinit var itemTrack: ItemTrack
    private lateinit var imgTrack: ImageView
    private lateinit var txtId: TextView
    private lateinit var txtName: TextView
    private lateinit var txtArtista: TextView
    private lateinit var txtFechaAnadido: TextView
    private lateinit var txtIdAnadido: TextView
    private lateinit var txtAlbum: TextView
    private lateinit var txtNumeroPista: TextView
    private lateinit var txtTypeTrack: TextView
    private lateinit var txtUriTrack: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        itemTrack = intent.extras!!.getBundle(Constants.BUNDLE_TRACK)!!.getSerializable(Constants.TRACK) as ItemTrack
    }

    override fun onStart() {
        super.onStart()
        val connectionParams = ConnectionParams.Builder(Constants.CLIENT_ID)
            .setRedirectUri(Constants.REDIRECT_URI)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams, object : ConnectionListener {
            override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                mSpotifyAppRemote = spotifyAppRemote
                connected()
            }
            override fun onFailure(throwable: Throwable) {
                Log.e(tag, throwable.message, throwable)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        initViews()
        loadValues()
    }

    override fun onStop() {
        super.onStop()
        SpotifyAppRemote.disconnect(mSpotifyAppRemote)
    }

    private fun initViews(){
        imgTrack = findViewById(R.id.imgTrack)
        txtId = findViewById(R.id.txtId)
        txtName = findViewById(R.id.txtName)
        txtArtista = findViewById(R.id.txtArtista)
        txtFechaAnadido = findViewById(R.id.txtFechaAnadido)
        txtIdAnadido = findViewById(R.id.txtIdAnadido)
        txtAlbum = findViewById(R.id.txtAlbum)
        txtNumeroPista = findViewById(R.id.txtNumeroPista)
        txtTypeTrack = findViewById(R.id.txtTypeTrack)
        txtUriTrack = findViewById(R.id.txtUriTrack)
    }

    private fun loadValues(){
        if(itemTrack.track.album.images != null) loadImage()
        txtId.text = itemTrack.track.id
        txtName.text = itemTrack.track.name

        val allNamesArtists = itemTrack.track.artists!!.map { a -> a.name }.joinToString { s -> s }
        txtArtista.text = allNamesArtists
        txtFechaAnadido.text = itemTrack.addedAt
        txtIdAnadido.text = itemTrack.addedBy.id
        txtAlbum.text = itemTrack.track.album.name
        txtNumeroPista.text = itemTrack.track.trackNumber.toString()
        txtTypeTrack.text = itemTrack.track.type
        txtUriTrack.text = itemTrack.track.uri
    }

    private fun loadImage(){
        Picasso.with(this).load(itemTrack.track.album.images!![positionListImage].url).into(imgTrack)
    }

    private fun connected() {
        mSpotifyAppRemote!!.playerApi.play(itemTrack.track.uri)
        mSpotifyAppRemote!!.playerApi
            .subscribeToPlayerState()
            .setEventCallback { playerState ->
                val track = playerState.track
                if (track != null) {
                    Log.d(tag, track.name + textBy + track.artist.name)
                }
            }
    }
}
