package com.vmn.data.di

import com.vmn.data.AuthRepoImpl
import com.vmn.data.HomeRepoImpl
import com.vmn.data.SharedPrefRepoImpl
import com.vmn.data.network.AuthService
import com.vmn.data.network.HomeService
import com.vmn.data.utils.ApiManager
import com.vmn.data.utils.NetworkManager
import com.vmn.data.utils.SharedPrefManager
import com.vmn.domain.repo.AuthRepo
import com.vmn.domain.repo.HomeRepo
import com.vmn.domain.repo.SharedPrefRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    //stateless and can be shared across the app
    single { ApiManager.retrofitInstance(HomeService::class.java) }
    single { ApiManager.retrofitInstance(AuthService::class.java) }
    single { SharedPrefRepoImpl(SharedPrefManager.prefInstance(androidContext())) } bind SharedPrefRepo::class
    single { NetworkManager(androidContext()) }
    //has a state and not planning to share across the app
    factory { AuthRepoImpl(get(), get()) } bind AuthRepo::class
    factory { HomeRepoImpl(get(), get(), get()) } bind HomeRepo::class
}