package com.vmn.domain.entity


import androidx.annotation.Keep

@Keep
data class InvitesEntity(
    val pendingInvitationsCount: Int,
    val profiles: List<InviteProfileEntity>,
    val totalPages: Int
)