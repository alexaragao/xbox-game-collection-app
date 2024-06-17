package com.xboxgamecollection.app.features.auth.di

import com.xboxgamecollection.app.features.auth.data.repository.AuthenticationRepository
import com.xboxgamecollection.app.features.auth.data.service.AuthenticationService
import com.xboxgamecollection.app.features.auth.domain.repository.IAuthenticationRepository
import com.xboxgamecollection.app.features.auth.domain.service.IAuthenticationService
import com.xboxgamecollection.app.features.auth.domain.usecase.AuthRegisterUseCase
import com.xboxgamecollection.app.features.auth.domain.usecase.AuthSignInUseCase
import com.xboxgamecollection.app.features.auth.domain.usecase.AuthenticateWithTokensUseCase
import com.xboxgamecollection.app.features.auth.domain.usecase.InstallAuthBearerUseCase
import com.xboxgamecollection.app.features.auth.domain.usecase.RemoveAuthBearerUseCase
import com.xboxgamecollection.app.network.services.apiClient
import org.koin.dsl.module

val authenticationModule = module {
    single { apiClient }
    single<IAuthenticationService> { AuthenticationService(get()) }
    single<IAuthenticationRepository> { AuthenticationRepository(get()) }
    single { AuthenticateWithTokensUseCase(get()) }
    single { InstallAuthBearerUseCase(get()) }
    single { RemoveAuthBearerUseCase(get()) }
    single { AuthSignInUseCase(get()) }
    single { AuthRegisterUseCase(get()) }
}
