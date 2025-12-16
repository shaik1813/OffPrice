package com.apparel.offprice.features.cart.data


data class BankCouponItems(
    val id: String,
    val title: String,
    val desc: String,
)

data class CouponItems(
    val title: String,
    val desc: String,
    val code: String,
)

val couponList = listOf(
    CouponItems(
        title = "Get ₹10 off*",
        code = "GET10",
        desc = "Maximum Cashback Limit is ₹10 off*"
    ),
    CouponItems(
        title = "30% Cashback",
        code = "CASHBACK30",
        desc = "Maximum Cashback Limit is ₹10 off*",
    ),
    CouponItems(
        title = "Extra 20% off*",
        code = "EXTRA20",
        desc = "Maximum Cashback Limit is ₹10 off*"
    )
)

val couponItem = BankCouponItems(
    "1", "With FAB Emirati Islamic Credit Card \nVali On All Categories",
    "(Min.Purchase AED 20)"
)