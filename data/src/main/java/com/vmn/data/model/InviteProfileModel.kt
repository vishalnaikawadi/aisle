package com.vmn.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class InviteProfileModel(

    @SerializedName("approved_time")
    val approvedTime: Double?,

    @SerializedName("disapproved_time")
    val disapprovedTime: Double?,

    @SerializedName("general_information")
    val generalInformation: GeneralInformationModel?,

    @SerializedName("has_active_subscription")
    val hasActiveSubscription: Boolean?,

    val photos: List<PhotoModel>?,

    @SerializedName("verification_status")
    val verificationStatus: String?
)