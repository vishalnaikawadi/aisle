package com.vmn.domain.di

import com.vmn.domain.usecase.AuthUseCase
import com.vmn.domain.usecase.AuthUseCaseImpl
import com.vmn.domain.usecase.NotesUseCase
import com.vmn.domain.usecase.NotesUseCaseImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {

    //using single for useCase because it is stateless and can be shared across the app
    single { AuthUseCaseImpl(get(), get()) } bind AuthUseCase::class
    single { NotesUseCaseImpl(get()) } bind NotesUseCase::class
}