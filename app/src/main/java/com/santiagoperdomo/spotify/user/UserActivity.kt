package com.santiagoperdomo.spotify.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.santiagoperdomo.spotify.R
import com.santiagoperdomo.spotify.common.Constants
import com.santiagoperdomo.spotify.common.SharedPreferencesManager
import com.santiagoperdomo.spotify.http.model.User
import com.santiagoperdomo.spotify.root.App
import javax.inject.Inject

class UserActivity : AppCompatActivity(), UserMVP.View {

    private val tag = "UserActivity"

    @Inject
    lateinit var presenter: UserMVP.Presenter

    private lateinit var txtId: TextView
    private lateinit var txtDisplayName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtFollowers: TextView
    private lateinit var txtProduct: TextView
    private lateinit var txtType: TextView
    private lateinit var txtUri: TextView

    private lateinit var recyclerPlaylists: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        (application as App).component.inject(this)
    }

    override fun onResume() {
        super.onResume()
        initViews()
        presenter.setView(this)
        Log.d(tag, SharedPreferencesManager.getSomeStringValue(Constants.AUTH_TOKEN))
    }

    private fun initViews(){
        txtId = findViewById(R.id.txtId)
        txtDisplayName = findViewById(R.id.txtDisplayName)
        txtEmail = findViewById(R.id.txtEmail)
        txtFollowers = findViewById(R.id.txtFollowers)
        txtProduct = findViewById(R.id.txtProduct)
        txtType = findViewById(R.id.txtType)
        txtUri = findViewById(R.id.txtUri)
        recyclerPlaylists = findViewById(R.id.recyclerPlaylists)
        recyclerPlaylists.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}
