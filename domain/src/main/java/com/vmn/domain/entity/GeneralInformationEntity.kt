package com.vmn.domain.entity


import androidx.annotation.Keep

@Keep
data class GeneralInformationEntity(
    val age: Int,
    val dateOfBirth: String,
    val firstName: String,
    val gender: String,
    val height: Int
)