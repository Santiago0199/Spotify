package com.santiagoperdomo.spotify

import com.santiagoperdomo.spotify.common.Constants
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