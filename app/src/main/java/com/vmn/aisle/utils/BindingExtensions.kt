package com.vmn.aisle.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import com.vmn.domain.entity.GeneralInformationEntity

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .into(this)
    }
}

@BindingAdapter("setError")
fun TextInputLayout.setManualError(errorMsg: String?) {
    error = errorMsg
}

@BindingAdapter("isVisible")
fun View.setVisibility(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("customWidth")
fun setLayoutWidth(view: View, width: Int) {
    view.layoutParams = view.layoutParams.apply { this.width = width }
}

@BindingAdapter("generalInfo")
fun TextView.setGeneralInfo(info: GeneralInformationEntity) {
    text = "${info.firstName}, ${info.age}"
}

@BindingAdapter("isResendEnabled", "countdown")
fun TextView.setCountDown(isEnabled: Boolean, countdown: String) {
    text = if (isEnabled) "Resend" else countdown
}