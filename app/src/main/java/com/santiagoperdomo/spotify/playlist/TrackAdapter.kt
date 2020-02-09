package com.santiagoperdomo.spotify.playlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.santiagoperdomo.spotify.R
import com.santiagoperdomo.spotify.common.Constants
import com.santiagoperdomo.spotify.http.model.ItemTrack
import com.santiagoperdomo.spotify.track.TrackActivity
import com.squareup.picasso.Picasso
import java.util.ArrayList

class TrackAdapter(listTracks: ArrayList<ItemTrack>): RecyclerView.Adapter<TrackAdapter.ViewHolder>(){

    var listTracks: ArrayList<ItemTrack>
    var copieList: ArrayList<ItemTrack>?
    lateinit var context: Context

    init {
        this.listTracks = ArrayList(listTracks)
        copieList = listTracks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_track, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listTracks[position]
        holder.titleTrack.setText(item.track.name)
        holder.idTrack.setText(item.track.id)
        if(item.track.album.images != null){
            val image = item.track.album.images
            Picasso.with(context).load(image!![0].url).into(holder.imgTrack)
        }
    }

    override fun getItemCount(): Int {
        return listTracks.size
    }

    fun addItem(itemTrack: ItemTrack){
        listTracks.add(itemTrack)
        copieList = ArrayList(listTracks)
        notifyDataSetChanged()
    }

    fun searchContact(searchText:String){
        listTracks.clear()
        if(searchText.isEmpty()){
            listTracks = ArrayList(copieList!!)
        }else{
            for(item in copieList!!){
                val name = item.track.name.toLowerCase()
                if(name.contains(searchText.toLowerCase())){
                    listTracks.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var titleTrack: TextView
        var idTrack: TextView
        var imgTrack: ImageView

        init {
            titleTrack = view.findViewById(R.id.titleTrack)
            idTrack = view.findViewById(R.id.idTrack)
            imgTrack = view.findViewById(R.id.imgTrack)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val bundlePlayList = Bundle()
            bundlePlayList.putSerializable(Constants.TRACK, listTracks[adapterPosition])
            val intent = Intent(context, TrackActivity::class.java)
            intent.putExtra(Constants.BUNDLE_TRACK, bundlePlayList)
            context.startActivity(intent)
        }
    }
}