package com.vmn.domain.entity


import androidx.annotation.Keep

@Keep
data class InviteProfileEntity(

    val approvedTime: Double,
    val disapprovedTime: Double,
    val generalInformation: GeneralInformationEntity?,
    val hasActiveSubscription: Boolean,
    val photos: List<PhotoEntity>,
    val verificationStatus: String
)