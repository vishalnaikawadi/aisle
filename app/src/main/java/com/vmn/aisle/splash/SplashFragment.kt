package com.vmn.aisle.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vmn.aisle.R
import com.vmn.aisle.core.BaseFragment
import com.vmn.aisle.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class SplashFragment : BaseFragment() {

    private val splashViewModel by inject<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOps()
    }

    private fun initOps() {

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            if (splashViewModel.isUserLoggedIn()) {
                gotoHome()
            } else {
                gotoLogin()
            }

        }
    }

    private fun gotoLogin() {
        val action = SplashFragmentDirections.actionSplashFragmentToPhoneNumberFragment()
        findNavController().navigate(action)
    }

    private fun gotoHome() {
        val action = SplashFragmentDirections.actionSplashFragmentToDiscoverFragment()
        findNavController().navigate(action)
    }

}