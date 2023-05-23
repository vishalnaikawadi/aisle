package com.vmn.aisle.auth.viewmodel

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmn.aisle.R
import com.vmn.aisle.auth.model.PhoneNumberBindingModel
import com.vmn.aisle.utils.StringResourceProvider
import com.vmn.domain.entity.PhoneVerificationEntity
import com.vmn.domain.usecase.AuthUseCase
import com.vmn.domain.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhoneNumberViewModel(
    private val useCase: AuthUseCase,
    private val resource: StringResourceProvider
) : ViewModel() {

    val bmPhoneNumber = PhoneNumberBindingModel()

    private var _verificationDetails = MutableLiveData<Resource<PhoneVerificationEntity?>?>()
    val verificationDetails: LiveData<Resource<PhoneVerificationEntity?>?>
        get() = _verificationDetails

    fun verifyPhoneNumber(number: String) {
        CoroutineScope(Dispatchers.IO).launch {
            showProgress()
            val response = useCase.verifyPhone(number)

            withContext(Dispatchers.Main) {
                hideProgress()
                when (response) {
                    is Resource.Success -> {
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
            bmPhoneNumber.countryCode.isEmpty() -> {
                bmPhoneNumber.countryCodeError = resource.getString(R.string.err_invalid_code)
                return
            }
            bmPhoneNumber.countryCode.length < 3 -> {
                bmPhoneNumber.countryCodeError = resource.getString(R.string.err_invalid_code)
                return
            }
            bmPhoneNumber.phoneNumber.isEmpty() -> {
                bmPhoneNumber.phoneNumberError = resource.getString(R.string.err_empty_field)
                return
            }
            bmPhoneNumber.phoneNumber.length < 10 -> {
                bmPhoneNumber.phoneNumberError = resource.getString(R.string.err_invalid_phone)
                return
            }
            else -> {
                verifyPhoneNumber(bmPhoneNumber.countryCode + bmPhoneNumber.phoneNumber)
            }
        }

    }

    val textWatcherPhone = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            bmPhoneNumber.phoneNumberError = null
        }

        override fun afterTextChanged(p0: Editable?) {}

    }

    val textWatcherCode = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            bmPhoneNumber.countryCodeError = null
        }

        override fun afterTextChanged(p0: Editable?) {}

    }


    private fun showProgress() {
        bmPhoneNumber.showProgress = true
    }

    private fun hideProgress() {
        bmPhoneNumber.showProgress = false
    }

    fun clear() {
        _verificationDetails.value = null
    }


}