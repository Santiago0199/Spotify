package com.santiagoperdomo.spotify.user

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.santiagoperdomo.spotify.R
import com.santiagoperdomo.spotify.http.model.Playlists
import com.squareup.picasso.Picasso

class PlaylistsAdapter(listPlaylists: List<Playlists>): RecyclerView.Adapter<PlaylistsAdapter.ViewHolder>(), View.OnClickListener{

    private val positionArrayImages = 0
    val listPlaylists: List<Playlists>
    lateinit var listenerClick: View.OnClickListener
    lateinit var context: Context

    init {
        this.listPlaylists = listPlaylists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_playlist, parent, false)
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPlaylists[position]
        holder.titlePlaylists.setText(item.name)
        holder.idPlaylists.setText(item.id)
        val images = item.images
        if(images != null && images.isNotEmpty()){
            val image = item.images
            Picasso.with(context).load(image!![positionArrayImages].url).into(holder.imgPlaylists)
        }
    }

    override fun getItemCount(): Int {
        return listPlaylists.size
    }

    fun initOnClickListener(listenerClick: View.OnClickListener){
        this.listenerClick = listenerClick
    }

    override fun onClick(p0: View?) {
        this.listenerClick.onClick(p0)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var titlePlaylists: TextView
        var idPlaylists: TextView
        var imgPlaylists: ImageView

        init {
            titlePlaylists = view.findViewById(R.id.titlePlaylists)
            idPlaylists = view.findViewById(R.id.idPlaylists)
            imgPlaylists = view.findViewById(R.id.imgPlaylist)
        }
    }

}