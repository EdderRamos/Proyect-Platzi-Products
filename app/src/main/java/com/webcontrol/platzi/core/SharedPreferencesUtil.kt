package com.webcontrol.platzi.core

import android.content.SharedPreferences


import javax.inject.Inject

class SharedPreferencesUtil @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveStateMode(state: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("mode_state", state)
        editor.apply()
    }

    fun getStateMode(): Boolean {
        return sharedPreferences.getBoolean("mode_state", true)
    }
}