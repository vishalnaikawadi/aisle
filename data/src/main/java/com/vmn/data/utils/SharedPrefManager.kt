package com.vmn.data.utils

import android.content.Context

object SharedPrefManager {

    private const val PREF_NAME = "aisle_pref"

    fun prefInstance(context: Context) =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
}