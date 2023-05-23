package com.vmn.domain.repo

import com.vmn.domain.entity.OtpVerificationEntity
import com.vmn.domain.entity.PhoneVerificationEntity
import com.vmn.domain.utils.Resource

interface AuthRepo {

    suspend fun verifyPhone(
        number: String
    ): Resource<PhoneVerificationEntity>

    suspend fun verifyOtp(
        number: String,
        otpCode: String
    ): Resource<OtpVerificationEntity>

}