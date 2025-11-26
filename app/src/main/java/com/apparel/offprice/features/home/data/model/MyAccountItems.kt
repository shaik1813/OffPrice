package com.apparel.offprice.features.home.data.model

import com.apparel.offprice.R

data class MyAccountItems(
    val title: String,
    val icon: Int,
    val categoryId: Int
)


val accountItems = listOf(
    MyAccountItems("User Profile", R.drawable.icon_user_profile,1),
    MyAccountItems("My Orders", R.drawable.icon_my_order,2),
    MyAccountItems("Returns", R.drawable.icon_returns,3),
    MyAccountItems("Exchange", R.drawable.icon_exchange,4),
    MyAccountItems("Store Credits", R.drawable.icon_store_credit,5),
    MyAccountItems("My Coupons", R.drawable.icon_coupons,6),
    MyAccountItems("Delivery Addresses", R.drawable.icon_delivery_address,7),
    MyAccountItems("Payment Cards", R.drawable.icon_payment_card,8),
    MyAccountItems("Store Locator", R.drawable.icon_gps,9),
    MyAccountItems("Log Out", R.drawable.icon_log_out,0)
)