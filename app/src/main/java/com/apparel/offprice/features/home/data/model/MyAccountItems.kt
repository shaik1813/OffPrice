package com.apparel.offprice.features.home.data.model

import com.apparel.offprice.R
import com.apparel.offprice.routes.AppScreen

data class MyAccountItems(
    val title: Int,
    val icon: Int,
    val categoryId: Int,
    val navigation : AppScreen
)


val accountItems = listOf(
    MyAccountItems(R.string.label_user_profile, R.drawable.icon_user_profile,1, AppScreen.UserProfileScreen),
    MyAccountItems(R.string.label_my_orders, R.drawable.icon_my_order,2, AppScreen.MyOrdersScreen),
    MyAccountItems(R.string.label_returns, R.drawable.icon_returns,3, AppScreen.ReturnsScreen),
    MyAccountItems(R.string.label_exchange, R.drawable.icon_exchange,4, AppScreen.ExchangeScreen),
    MyAccountItems(R.string.label_store_credit, R.drawable.icon_store_credit,5, AppScreen.StoreCreditScreen),
    MyAccountItems(R.string.label_my_coupons, R.drawable.icon_coupons,6 , AppScreen.CouponScreen),
    MyAccountItems(R.string.label_delivery_address, R.drawable.icon_delivery_address,7, AppScreen.DeliveryAddressScreen),
    MyAccountItems(R.string.label_payment_cards, R.drawable.icon_payment_card,8, AppScreen.PaymentCardScreen),
    MyAccountItems(R.string.label_store_locator, R.drawable.icon_gps,9 , AppScreen.StoreLocatorScreen),
    MyAccountItems(R.string.label_log_out, R.drawable.icon_log_out,0, AppScreen.LogOutScreen)
)