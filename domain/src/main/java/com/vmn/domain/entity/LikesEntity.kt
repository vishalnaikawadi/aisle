package com.vmn.domain.entity


import androidx.annotation.Keep

@Keep
data class LikesEntity(
    val canSeeProfile: Boolean,
    val likesReceivedCount: Int,
    val profiles: List<LikesProfileEntity>
)