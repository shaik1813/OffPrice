package com.apparel.offprice.routes

import kotlinx.serialization.Serializable

sealed interface AppScreen {
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
    data class HomeScreen(val navId:Int = 0) : AppScreen

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
    data class PDPScreen(val id: String) : AppScreen

    @Serializable
    data class PLPScreen(val title: String) : AppScreen

    @Serializable
    object UserProfileScreen : AppScreen

    @Serializable
    object ProfileDetailScreen : AppScreen

    @Serializable
    object ProfileSizeScreen : AppScreen

    @Serializable
    object ProfilePasswordScreen : AppScreen

    @Serializable
    object StoreCreditScreen : AppScreen

    @Serializable
    object DeliveryAddressScreen : AppScreen


    @Serializable
    object ShippingAddressScreen : AppScreen

    @Serializable
    object CouponScreen : AppScreen

    @Serializable
    object PaymentCardScreen : AppScreen

    @Serializable
    object MyOrdersScreen : AppScreen

    @Serializable
    object ReturnsScreen : AppScreen

    @Serializable
    data class ReturnDetailsScreen(val returnId: String) : AppScreen

    @Serializable
    object ExchangeScreen : AppScreen

    @Serializable
    data class SubCategoryScreen(val title: String) : AppScreen

    @Serializable
    data object CustomerSupportScreen : AppScreen

    @Serializable
    data object AboutUsScreen : AppScreen

    @Serializable
    data object DisclaimerScreen : AppScreen

    @Serializable
    data object ContactInquiryScreen : AppScreen

    @Serializable
    data object ReturnPolicyScreen : AppScreen

    @Serializable
    data object TermsAndConditionScreen : AppScreen

    @Serializable
    data object ShippingInfoScreen : AppScreen

    @Serializable
    data object PrivacyPolicyScreen : AppScreen


}
