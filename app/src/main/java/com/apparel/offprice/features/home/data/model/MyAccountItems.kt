package com.apparel.offprice.features.home.data.model

import com.apparel.offprice.R

data class MyAccountItems(
    val title: Int,
    val icon: Int,
    val categoryId: Int
)


val accountItems = listOf(
    MyAccountItems(R.string.label_user_profile, R.drawable.icon_user_profile,1),
    MyAccountItems(R.string.label_my_orders, R.drawable.icon_my_order,2),
    MyAccountItems(R.string.label_returns, R.drawable.icon_returns,3),
    MyAccountItems(R.string.label_exchange, R.drawable.icon_exchange,4),
    MyAccountItems(R.string.label_store_credit, R.drawable.icon_store_credit,5),
    MyAccountItems(R.string.label_my_coupons, R.drawable.icon_coupons,6),
    MyAccountItems(R.string.label_delivery_address, R.drawable.icon_delivery_address,7),
    MyAccountItems(R.string.label_payment_cards, R.drawable.icon_payment_card,8),
    MyAccountItems(R.string.label_store_locator, R.drawable.icon_gps,9),
    MyAccountItems(R.string.label_log_out, R.drawable.icon_log_out,0)
)