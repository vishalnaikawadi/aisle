package com.vmn.data.network

import com.vmn.data.model.OtpVerificationModel
import com.vmn.data.model.PhoneVerificationModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @POST("/V1/users/phone_number_login")
    @FormUrlEncoded
    suspend fun verifyPhone(
        @Field("number") number: String
    ): PhoneVerificationModel?

    @POST("/V1/users/verify_otp")
    @FormUrlEncoded
    suspend fun verifyOTP(
        @Field("number") number: String,
        @Field("otp") otpCode: String
    ): OtpVerificationModel?

}