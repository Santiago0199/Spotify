package com.santiagoperdomo.spotify.playlist

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.santiagoperdomo.spotify.R
import com.santiagoperdomo.spotify.common.Constants
import com.santiagoperdomo.spotify.http.model.ItemTrack
import com.santiagoperdomo.spotify.http.model.Playlists
import com.santiagoperdomo.spotify.root.App
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PlaylistsActivity : AppCompatActivity(), PlaylistMVP.View {

    val textYes = "Si"
    val textNo = "No"
    val hintSearch = "Buscar por nombre..."

    @Inject
    lateinit var presenter: PlaylistMVP.Presenter

    lateinit var progress: SweetAlertDialog

    private lateinit var itemPlaylist: Playlists
    private lateinit var imgPlaylist: ImageView
    private lateinit var txtIdTrack: TextView
    private lateinit var txtNameTrack: TextView
    private lateinit var txtOwnerIdTrack: TextView
    private lateinit var txtPublicoTrack: TextView
    private lateinit var txtCollaborativeTrack: TextView
    private lateinit var txtTypeTrack: TextView
    private lateinit var txtUriTrack: TextView

    private lateinit var listItemTrack: ArrayList<ItemTrack>
    private lateinit var recyclerItemTrack: RecyclerView
    private lateinit var adapterItemTrack: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlists)

        (application as App).component.inject(this)

        itemPlaylist = intent.extras!!.getBundle(Constants.BUNDLE_PLAYLIST)!!.getSerializable(Constants.PLAYLIST) as Playlists
    }

    override fun onResume() {
        super.onResume()
        showProgress()
        initViews()
        loadValues()
        presenter.setView(this)
        presenter.loadTrack(itemPlaylist.id)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateTrack(itemTrack: ItemTrack) {
        adapterItemTrack.addItem(itemTrack)
    }

    override fun successRequest() {
        progress.dismiss()
    }

    private fun showProgress(){
        progress = SweetAlertDialog(this)
        progress.changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
        progress.setTitle(Constants.TEXT_PROGRESS)
        progress.setCancelable(false)
        progress.progressHelper.barColor = Color.parseColor(Constants.COLOR_PROGRESS)
        progress.show()
    }

    private fun initViews(){
        imgPlaylist = findViewById(R.id.imgPlaylist)
        txtIdTrack = findViewById(R.id.txtIdTrack)
        txtNameTrack = findViewById(R.id.txtNameTrack)
        txtOwnerIdTrack = findViewById(R.id.txtOwnerIdTrack)
        txtPublicoTrack = findViewById(R.id.txtPublicoTrack)
        txtCollaborativeTrack = findViewById(R.id.txtCollaborativeTrack)
        txtTypeTrack = findViewById(R.id.txtTypeTrack)
        txtUriTrack = findViewById(R.id.txtUriTrack)
        listItemTrack = ArrayList()
        recyclerItemTrack = findViewById(R.id.recyclerTracks)
        recyclerItemTrack.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapterItemTrack = TrackAdapter(listItemTrack)
        recyclerItemTrack.adapter = adapterItemTrack
    }

    private fun loadValues(){
        if(itemPlaylist.images != null) loadImage()
        txtIdTrack.text = itemPlaylist.id
        txtNameTrack.text = itemPlaylist.name
        txtOwnerIdTrack.text = itemPlaylist.owner.id
        if(itemPlaylist.public) txtPublicoTrack.text = textYes else txtPublicoTrack.text = textNo
        if(itemPlaylist.collaborative) txtCollaborativeTrack.text = textYes else txtCollaborativeTrack.text = textNo
        txtTypeTrack.text = itemPlaylist.type
        txtUriTrack.text = itemPlaylist.uri
    }

    private fun loadImage(){
        Picasso.with(this).load(itemPlaylist.images!![0].url).into(imgPlaylist)
    }
}
