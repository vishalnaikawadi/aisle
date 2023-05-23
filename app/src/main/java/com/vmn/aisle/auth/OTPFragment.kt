package com.vmn.aisle.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vmn.aisle.R
import com.vmn.aisle.auth.viewmodel.OTPViewModel
import com.vmn.aisle.core.BaseFragment
import com.vmn.aisle.databinding.FragmentOTPBinding
import com.vmn.domain.utils.Resource
import org.koin.android.ext.android.inject


class OTPFragment : BaseFragment() {

    private lateinit var binding: FragmentOTPBinding
    private val otpViewModel by inject<OTPViewModel>()
    private val args: OTPFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOTPBinding.inflate(inflater, container, false)
        binding.viewModel = otpViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOps()
        observers()
        listeners()
    }

    private fun initOps() {
        otpViewModel.setPhoneNumber(args.number)
        binding.etOtp.addTextChangedListener(otpViewModel.textWatcherOtp)
    }

    private fun observers() {

        otpViewModel.verificationDetails.observe(viewLifecycleOwner) {
            it?.let { res ->

                when (res) {
                    is Resource.Success -> {
                        navigateToHome()
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

    private fun navigateToHome() {
        val action = OTPFragmentDirections.actionOTPFragmentToDiscoverFragment()
        findNavController().navigate(action)
    }

    private fun listeners() {
        binding.btnContinue.setOnClickListener {
            otpViewModel.handleAndProceed()
        }

        binding.imgEdit.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}