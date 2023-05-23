package com.vmn.aisle.auth.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.vmn.aisle.BR

class OtpBindingModel : BaseObservable() {

    @get:Bindable
    var otpCode = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.otpCode)
        }

    @get:Bindable
    var phoneNumber = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.phoneNumber)
        }

    @get:Bindable
    var otpCodeError: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.otpCodeError)
        }

    @get:Bindable
    var showProgress = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showProgress)
        }

    @get:Bindable
    var isResendEnabled = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.resendEnabled)
        }

    @get:Bindable
    var countDown = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.countDown)
        }
}