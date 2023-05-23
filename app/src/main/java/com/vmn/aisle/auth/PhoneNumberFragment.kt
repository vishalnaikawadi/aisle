package com.vmn.aisle.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vmn.aisle.R
import com.vmn.aisle.auth.viewmodel.PhoneNumberViewModel
import com.vmn.aisle.core.BaseFragment
import com.vmn.aisle.databinding.FragmentPhoneNumberBinding
import com.vmn.domain.utils.Resource
import org.koin.android.ext.android.inject


class PhoneNumberFragment : BaseFragment() {

    private lateinit var binding: FragmentPhoneNumberBinding
    private val phoneViewModel by inject<PhoneNumberViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhoneNumberBinding.inflate(inflater, container, false)
        binding.viewModel = phoneViewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOps()
        observers()
        listeners()
    }

    private fun initOps() {
        binding.etNumber.addTextChangedListener(phoneViewModel.textWatcherPhone)
        binding.etCode.addTextChangedListener(phoneViewModel.textWatcherCode)
    }

    private fun observers() {

        phoneViewModel.verificationDetails.observe(viewLifecycleOwner) {
            it?.let { res ->

                when (res) {
                    is Resource.Success -> {
                        navigateToOTP()
                    }

                    is Resource.Error -> {
                        showShortMessage(res.errorMessage)
                    }

                    Resource.NoInternet -> {
                        showShortMessage(getString(R.string.no_internet))
                    }

                    Resource.Unknown -> {
                        showShortMessage(getString(R.string.error_unknown))
                    }
                }
            }
        }
    }

    private fun navigateToOTP() {
        val action = PhoneNumberFragmentDirections.actionPhoneNumberFragmentToOTPFragment(
            phoneViewModel.bmPhoneNumber.countryCode + phoneViewModel.bmPhoneNumber.phoneNumber
        )
        findNavController().navigate(action)
    }

    private fun listeners() {
        binding.btnContinue.setOnClickListener {
            phoneViewModel.handleAndProceed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        phoneViewModel.clear()
    }
}