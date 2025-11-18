package com.apparel.offprice.routes

import kotlinx.serialization.Serializable

sealed interface AppScreen{
    @Serializable
    object SplashScreen : AppScreen

    @Serializable
    object LoginScreen : AppScreen

    @Serializable
    object ForgetPasswordScreen : AppScreen

    @Serializable
    object RegistrationScreen : AppScreen

    @Serializable
    object OTPScreen : AppScreen

    @Serializable
    object HomeScreen: AppScreen
}
