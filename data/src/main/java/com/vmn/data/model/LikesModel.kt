package com.vmn.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LikesModel(
    @SerializedName("can_see_profile")
    val canSeeProfile: Boolean?,

    @SerializedName("likes_received_count")
    val likesReceivedCount: Int?,

    val profiles: List<LikesProfileModel>?
)