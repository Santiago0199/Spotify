package com.santiagoperdomo.spotify.common

import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse

class RequestToken {

    companion object{
        fun requestToken(): AuthenticationRequest {
            return AuthenticationRequest.Builder(
                Constants.CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                Constants.REDIRECT_URI
            ).setScopes(arrayOf("streaming")).build()
        }
    }

}