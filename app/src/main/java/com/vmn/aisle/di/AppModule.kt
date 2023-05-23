package com.vmn.aisle.di

import com.vmn.aisle.auth.viewmodel.OTPViewModel
import com.vmn.aisle.auth.viewmodel.PhoneNumberViewModel
import com.vmn.aisle.home.viewmodel.NotesViewModel
import com.vmn.aisle.splash.viewmodel.SplashViewModel
import com.vmn.aisle.utils.AndroidStringResourceProvider
import com.vmn.aisle.utils.StringResourceProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    viewModel { OTPViewModel(get(), get()) }
    viewModel { PhoneNumberViewModel(get(), get()) }
    viewModel { NotesViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    single { AndroidStringResourceProvider(androidContext()) } bind StringResourceProvider::class
}