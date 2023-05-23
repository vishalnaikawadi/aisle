package com.vmn.domain.usecase

import com.vmn.domain.entity.OtpVerificationEntity
import com.vmn.domain.entity.PhoneVerificationEntity
import com.vmn.domain.repo.AuthRepo
import com.vmn.domain.repo.SharedPrefRepo
import com.vmn.domain.utils.Resource
import com.vmn.domain.utils.SharedPrefConstants

interface AuthUseCase {

    suspend fun verifyPhone(
        number: String
    ): Resource<PhoneVerificationEntity>

    suspend fun verifyOtp(
        number: String,
        otpCode: String
    ): Resource<OtpVerificationEntity>

    fun saveAuthToken(token: String)
    fun retrieveAuthToken(): String

}

class AuthUseCaseImpl(
    private val repo: AuthRepo,
    private val pref: SharedPrefRepo
) : AuthUseCase {
    override suspend fun verifyPhone(number: String): Resource<PhoneVerificationEntity> {
        return repo.verifyPhone(number)
    }

    override suspend fun verifyOtp(
        number: String,
        otpCode: String
    ): Resource<OtpVerificationEntity> {
        return repo.verifyOtp(number, otpCode)
    }

    override fun saveAuthToken(token: String) {
        pref.saveData(SharedPrefConstants.TOKEN, token)
    }

    override fun retrieveAuthToken(): String {
        return pref.loadData(SharedPrefConstants.TOKEN, "")
    }


}