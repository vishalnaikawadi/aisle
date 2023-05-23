package com.vmn.aisle.auth.viewmodel

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmn.aisle.R
import com.vmn.aisle.auth.model.OtpBindingModel
import com.vmn.aisle.utils.StringResourceProvider
import com.vmn.domain.entity.OtpVerificationEntity
import com.vmn.domain.usecase.AuthUseCase
import com.vmn.domain.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class OTPViewModel(
    private val useCase: AuthUseCase,
    private val resource: StringResourceProvider
) : ViewModel() {

    val bmOtp = OtpBindingModel()

    private var _verificationDetails = MutableLiveData<Resource<OtpVerificationEntity?>?>()
    val verificationDetails: LiveData<Resource<OtpVerificationEntity?>?>
        get() = _verificationDetails

    var countDownTimer: CountDownTimer? = null

    fun setPhoneNumber(phone: String) {
        bmOtp.phoneNumber = phone
    }

    init {
        setTimer()
    }

    fun verifyOtp(number: String, otpCode: String) {
        CoroutineScope(Dispatchers.IO).launch {
            showProgress()
            val response = useCase.verifyOtp(number, otpCode)

            withContext(Dispatchers.Main) {
                hideProgress()
                when (response) {
                    is Resource.Success -> {
                        useCase.saveAuthToken(response.data.token)
                        _verificationDetails.postValue(Resource.Success(response.data))
                    }

                    is Resource.Error -> {
                        _verificationDetails.postValue(Resource.Error(response.errorMessage))
                    }

                    Resource.NoInternet -> {
                        _verificationDetails.postValue(Resource.NoInternet)
                    }

                    Resource.Unknown -> {
                        _verificationDetails.postValue(Resource.Unknown)
                    }
                }
            }
        }
    }

    fun handleAndProceed() {

        when {
            bmOtp.otpCode.isEmpty() -> {
                bmOtp.otpCodeError = resource.getString(R.string.err_invalid_code)
                return
            }
            bmOtp.otpCode.length < 3 -> {
                bmOtp.otpCodeError = resource.getString(R.string.err_invalid_code)
                return
            }

            else -> {
                verifyOtp(bmOtp.phoneNumber, bmOtp.otpCode)
            }
        }

    }

    private fun setTimer() {
        bmOtp.isResendEnabled = false
        countDownTimer = object : CountDownTimer(59000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {

                bmOtp.countDown =
                    String.format("00:%d", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished))
            }

            override fun onFinish() {
                bmOtp.isResendEnabled = true
                bmOtp.countDown = ""
            }
        }
        countDownTimer?.start()
    }


    val textWatcherOtp = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            bmOtp.otpCodeError = null
        }

        override fun afterTextChanged(p0: Editable?) {}

    }


    private fun showProgress() {
        bmOtp.showProgress = true
    }

    private fun hideProgress() {
        bmOtp.showProgress = false
    }

    fun clear() {
        _verificationDetails.value = null
    }
}