package com.vmn.data.model

import androidx.annotation.Keep

@Keep
data class NotesModel(
    val invites: InvitesModel?,
    val likes: LikesModel?
)
