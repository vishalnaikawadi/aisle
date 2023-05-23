package com.vmn.aisle.splash.viewmodel

import androidx.lifecycle.ViewModel
import com.vmn.domain.repo.SharedPrefRepo
import com.vmn.domain.utils.SharedPrefConstants

class SplashViewModel(
    private val sharedPrefRepo: SharedPrefRepo
) : ViewModel() {

    fun isUserLoggedIn() = sharedPrefRepo.loadData(SharedPrefConstants.TOKEN, "").isNotEmpty()

}