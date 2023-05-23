package com.vmn.domain.entity


import androidx.annotation.Keep

@Keep
data class OtpVerificationEntity(
    val token: String
)