package com.santiagoperdomo.spotify.common

import android.content.Context
import android.content.SharedPreferences
import com.santiagoperdomo.spotify.root.App

class SharedPreferencesManager {

    companion object{

        fun getSharedPreference(): SharedPreferences {
            return App.instance.getSharedPreferences(Constants.APP_SETTINGS_FILE, Context.MODE_PRIVATE)
        }

        fun setSomeStringValue(key: String, value: String){
            val editor = getSharedPreference().edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getSomeStringValue(key: String): String{
            return getSharedPreference().getString(key, "")!!
        }

    }

}