package com.santiagoperdomo.spotify.user

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.santiagoperdomo.spotify.R
import com.santiagoperdomo.spotify.common.Constants
import com.santiagoperdomo.spotify.common.SharedPreferencesManager
import com.santiagoperdomo.spotify.http.model.Playlists
import com.santiagoperdomo.spotify.http.model.User
import com.santiagoperdomo.spotify.playlist.PlaylistsActivity
import com.santiagoperdomo.spotify.root.App
import javax.inject.Inject

class UserActivity : AppCompatActivity(), UserMVP.View {

    private val tag = "UserActivity"
    private val hintNamePlaylist = "Nombre del playlist"
    private val failUserNull = "No se han podido cargar los datos del usuario, vuelve a iniciar sesión"

    @Inject
    lateinit var presenter: UserMVP.Presenter

    private var user: User? = null
    lateinit var progress: SweetAlertDialog
    private lateinit var txtId: TextView
    private lateinit var txtDisplayName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtFollowers: TextView
    private lateinit var txtProduct: TextView
    private lateinit var txtType: TextView
    private lateinit var txtUri: TextView

    private lateinit var listPlaylists: ArrayList<Playlists>
    private lateinit var recyclerPlaylists: RecyclerView
    private lateinit var adapterPlaylists: PlaylistsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        (application as App).component.inject(this)
    }

    override fun onResume() {
        super.onResume()
        showProgress()
        initViews()
        events()
        presenter.setView(this)
        presenter.loadUserProfile()
        presenter.loadPlaylists()
        Log.d(tag, SharedPreferencesManager.getSomeStringValue(Constants.AUTH_TOKEN))
    }

    override fun onStop() {
        super.onStop()
        presenter.unsuscribeUserProfile()
        presenter.unsuscribePlaylists()
        listPlaylists.clear()
        adapterPlaylists.notifyDataSetChanged()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateUserProfile(user: User) {
        this.user = user
        txtId.text = user.id
        txtDisplayName.text = user.displayName
        txtEmail.text = user.email
        txtProduct.text = user.product
        txtType.text = user.type
        txtUri.text = user.uri
        txtFollowers.text = user.followers.total.toString()
    }

    override fun updatePlaylists(itemPlaylists: Playlists) {
        listPlaylists.add(itemPlaylists)
        adapterPlaylists.notifyDataSetChanged()
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
        txtId = findViewById(R.id.txtId)
        txtDisplayName = findViewById(R.id.txtDisplayName)
        txtEmail = findViewById(R.id.txtEmail)
        txtFollowers = findViewById(R.id.txtFollowers)
        txtProduct = findViewById(R.id.txtProduct)
        txtType = findViewById(R.id.txtType)
        txtUri = findViewById(R.id.txtUri)
        listPlaylists = ArrayList()
        recyclerPlaylists = findViewById(R.id.recyclerPlaylists)
        recyclerPlaylists.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapterPlaylists = PlaylistsAdapter(listPlaylists)
        recyclerPlaylists.adapter = adapterPlaylists
    }


    private fun events(){
        adapterPlaylists.initOnClickListener(View.OnClickListener {
            val bundlePlayList = Bundle()
            bundlePlayList.putSerializable(Constants.PLAYLIST, listPlaylists[recyclerPlaylists.getChildAdapterPosition(it)])
            val intent = Intent(this, PlaylistsActivity::class.java)
            intent.putExtra(Constants.BUNDLE_PLAYLIST, bundlePlayList)
            startActivity(intent)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.add -> {
                dialogNewPlaylist()
                true
            } else -> true
        }
    }

    private fun dialogNewPlaylist(){
        val alert = AlertDialog.Builder(this)
        val input = EditText(this)
        input.hint = hintNamePlaylist
        alert.setView(input)
        alert.setPositiveButton(Constants.ACCEPT) { dialog, _ ->
            if(eventDialogPlaylist(input.text.toString().trim { it <= ' ' })) dialog.dismiss()
        }
        alert.setNegativeButton(Constants.CANCEL) { dialog, _ -> dialog.cancel() }
        alert.show()
    }

    private fun eventDialogPlaylist(value: String): Boolean{
        if(user != null)
            if(presenter.validateNamePlaylist(value)){
                presenter.createdPlaylist(value, user!!.id)
                return true
            } else showToast(Constants.EMPTY_NAME_PLAYLIST)
        else showToast(failUserNull)
        return false
    }
}
