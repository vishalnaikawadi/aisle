package com.vmn.domain.entity


import androidx.annotation.Keep

@Keep
data class LikesProfileEntity(
    val avatar: String,
    val firstName: String
)