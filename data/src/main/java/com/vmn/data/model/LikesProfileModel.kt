package com.vmn.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LikesProfileModel(

    val avatar: String?,

    @SerializedName("first_name")
    val firstName: String?
)