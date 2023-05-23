package com.vmn.data

import com.vmn.data.network.AuthService
import com.vmn.data.utils.NetworkManager
import com.vmn.data.utils.map
import com.vmn.domain.entity.OtpVerificationEntity
import com.vmn.domain.entity.PhoneVerificationEntity
import com.vmn.domain.repo.AuthRepo
import com.vmn.domain.repo.SharedPrefRepo
import com.vmn.domain.utils.Resource

class AuthRepoImpl(
    private val service: AuthService,
    private val networkManager: NetworkManager
) : AuthRepo {
    override suspend fun verifyPhone(number: String): Resource<PhoneVerificationEntity> {
        return if (networkManager.isNetworkAvailable()) {

            try {
                val response =
                    service.verifyPhone(
                        number = number
                    )

                response?.let {
                    if (it.status == true) {
                        Resource.Success(it.map())
                    } else {
                        Resource.Error("Invalid User")
                    }
                } ?: run {
                    Resource.Error("No new data available")
                }

            } catch (ex: Exception) {
                Resource.Unknown
            }

        } else {
            Resource.NoInternet
        }

    }

    override suspend fun verifyOtp(
        number: String,
        otpCode: String
    ): Resource<OtpVerificationEntity> {
        return if (networkManager.isNetworkAvailable()) {

            try {
                val response =
                    service.verifyOTP(
                        number = number,
                        otpCode = otpCode
                    )

                response?.let {
                    Resource.Success(it.map())
                } ?: run {
                    Resource.Error("No new data available")
                }

            } catch (ex: Exception) {
                Resource.Unknown
            }

        } else {
            Resource.NoInternet
        }

    }
}