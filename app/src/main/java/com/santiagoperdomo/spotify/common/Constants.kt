package com.santiagoperdomo.spotify.common

class Constants {

    companion object{
        val BASE_URL_SPOTIFY = "https://api.spotify.com/v1/"

        val CLIENT_ID = "940e6765543547d6b858af64ff3e56fa"
        val REDIRECT_URI = "http://com.santiagoperdomo.spotify/callback"
        val REQUEST_CODE = 1337

        val APP_SETTINGS_FILE = "APP_SETTINGS"

        val AUTH_TOKEN = "AUTH_TOKEN"

        val TEXT_PROGRESS = "Cargando..."
        val COLOR_PROGRESS = "#009624"

        val BUNDLE_PLAYLIST = "BUNDLE_PLAYLIST"
        val PLAYLIST = "PLAYLIST"

        val BUNDLE_TRACK = "BUNDLE_PLAYLIST"
        val TRACK = "PLAYLIST"
    }

}