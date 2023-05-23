package com.vmn.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class InvitesModel(
    @SerializedName("pending_invitations_count")
    val pendingInvitationsCount: Int?,

    @SerializedName("profiles")
    val profiles: List<InviteProfileModel>?,

    val totalPages: Int?
)