package com.vmn.domain.entity


import androidx.annotation.Keep

@Keep
data class PhotoEntity(
    val photo: String?,
    val selected: Boolean?,
    val status: String?
)