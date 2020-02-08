package com.santiagoperdomo.spotify.http

import com.santiagoperdomo.spotify.common.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class SpotifyApiModule {

    @Provides
    fun provideHttpClient(): OkHttpClient{
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(AuthInterceptor())
        return okHttpClientBuilder.build()
    }

    @Provides
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideSpotifyApiService(): SpotifyApiService{
        return provideRetrofit(Constants.BASE_URL_SPOTIFY, provideHttpClient()).create(SpotifyApiService::class.java)
    }

}