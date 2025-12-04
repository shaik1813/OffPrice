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
    object ResetPasswordScreen : AppScreen

    @Serializable
    object RegistrationScreen : AppScreen

    @Serializable
    object OTPScreen : AppScreen

    @Serializable
    object HomeScreen: AppScreen

    @Serializable
    object SearchScreen : AppScreen

    @Serializable
    object StoreLocatorScreen : AppScreen

    @Serializable
    object WishListScreen : AppScreen

    @Serializable
    object GenderCategoryScreen : AppScreen

    @Serializable
    object LocationSelectionScreen : AppScreen

    @Serializable
    object PDPScreen : AppScreen

}
