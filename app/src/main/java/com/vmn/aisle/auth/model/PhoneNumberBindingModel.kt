package com.vmn.aisle.auth.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.vmn.aisle.BR

class PhoneNumberBindingModel : BaseObservable() {

    @get:Bindable
    var phoneNumber = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.phoneNumber)
        }

    @get:Bindable
    var countryCode = "+91"
        set(value) {
            field = value
            notifyPropertyChanged(BR.countryCode)
        }

    @get:Bindable
    var phoneNumberError: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.phoneNumberError)
        }

    @get:Bindable
    var countryCodeError: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.countryCodeError)
        }

    @get:Bindable
    var showProgress = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showProgress)
        }
}