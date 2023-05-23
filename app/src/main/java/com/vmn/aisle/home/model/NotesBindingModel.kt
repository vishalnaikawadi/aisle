package com.vmn.aisle.home.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.vmn.aisle.BR

class NotesBindingModel : BaseObservable() {

    @get:Bindable
    var showProgress = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showProgress)
        }


    @get:Bindable
    var showInitialLayouts = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showInitialLayouts)
        }

}