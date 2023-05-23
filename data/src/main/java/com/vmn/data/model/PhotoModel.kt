package com.vmn.data.model


import androidx.annotation.Keep

@Keep
data class PhotoModel(

    val photo: String?,

    val selected: Boolean?,

    val status: String?
)