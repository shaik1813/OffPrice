package com.apparel.offprice.features.coupon.data


data class CouponModel(
    val code: String,
    val offerDescription: String,
    val maxCashbackLimit: String,
)

data class BankCouponModel(
    val id: String,
    val discountPercentage: Int,
    val description: String,
    val minPurchase: String,
)

val sampleCouponList = listOf(
    CouponModel(
        code = "GET10",
        offerDescription = "Get Ɖ10 off*",
        maxCashbackLimit = "10 off",
    ),
    CouponModel(
        code = "CASHBACK30",
        offerDescription = "30% Cashback",
        maxCashbackLimit = "10 off",
    ),
    CouponModel(
        code = "EXTRA20",
        offerDescription = "Extra 20% off*",
        maxCashbackLimit = "10 off",
    ),
)

val sampleBankCouponList = listOf(
    BankCouponModel(
        id = "FAB_25",
        discountPercentage = 25,
        description = "with FAB Emirati Islamic Credit card! Valid on all categories",
        minPurchase = "(Min. purchase AED 20)",
    ),
)

