package com.vmn.data

import android.content.SharedPreferences
import com.vmn.domain.repo.SharedPrefRepo

class SharedPrefRepoImpl(
    private val sharedPref: SharedPreferences
) : SharedPrefRepo {

    override fun <T> saveData(key: String, value: T) {
        val editor = sharedPref.edit()
        when (value) {
            is String -> editor.putString(key, value as String)
            is Int -> editor.putInt(key, value as Int)
            is Boolean -> editor.putBoolean(key, value as Boolean)
        }
        editor.apply()
    }

    override fun <T> loadData(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> sharedPref.getString(key, defaultValue as String) as T
            is Int -> sharedPref.getInt(key, defaultValue as Int) as T
            is Boolean -> sharedPref.getBoolean(key, defaultValue as Boolean) as T
            else -> defaultValue
        }
    }
}