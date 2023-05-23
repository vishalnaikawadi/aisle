package com.vmn.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GeneralInformationModel(
    val age: Int?,

    @SerializedName("date_of_birth")
    val dateOfBirth: String?,

    @SerializedName("first_name")
    val firstName: String?,

    val gender: String?,

    val height: Int?
)