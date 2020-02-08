package com.santiagoperdomo.spotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.santiagoperdomo.spotify.common.Constants
import com.santiagoperdomo.spotify.common.RequestToken
import com.santiagoperdomo.spotify.common.SharedPreferencesManager
import com.santiagoperdomo.spotify.root.App
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    private lateinit var btnAuth: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).component.inject(this)
        AuthenticationClient.openLoginActivity(this, Constants.REQUEST_CODE, RequestToken.requestToken())
        initView()
        events()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (requestCode == Constants.REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)
            when(response.type) {
                AuthenticationResponse.Type.TOKEN -> {
                    SharedPreferencesManager.setSomeStringValue(Constants.AUTH_TOKEN, response.accessToken)
                    Log.d(tag, response.accessToken)
                }
                AuthenticationResponse.Type.ERROR -> {
                    Log.e(tag, response.error)
                }else -> {}
            }
        }
    }

    private fun initView(){
        btnAuth = findViewById(R.id.btnAuth)
    }

    private fun events(){
        btnAuth.setOnClickListener { AuthenticationClient.openLoginActivity(this, Constants.REQUEST_CODE, RequestToken.requestToken()) }
    }
}
