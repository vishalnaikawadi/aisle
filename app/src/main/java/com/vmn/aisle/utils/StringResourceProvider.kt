package com.vmn.aisle.utils

import android.content.Context

interface StringResourceProvider {
    fun getString(resId: Int): String
}

class AndroidStringResourceProvider(private val context: Context) : StringResourceProvider {
    override fun getString(resId: Int): String {
        return context.resources.getString(resId)
    }
}
