package com.santiagoperdomo.spotify.http

import com.santiagoperdomo.spotify.common.Constants
import com.santiagoperdomo.spotify.common.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = SharedPreferencesManager.getSomeStringValue(Constants.AUTH_TOKEN)
        val request = chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
        return chain.proceed(request)
    }

}