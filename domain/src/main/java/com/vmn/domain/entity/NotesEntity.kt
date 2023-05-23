package com.vmn.domain.entity

import androidx.annotation.Keep

@Keep
data class NotesEntity(
    val invites: InvitesEntity?,
    val likes: LikesEntity?
)
