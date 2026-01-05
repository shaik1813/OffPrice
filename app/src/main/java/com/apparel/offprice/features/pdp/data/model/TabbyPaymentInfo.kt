package com.apparel.offprice.features.pdp.data.model

data class TabbyPaymentInfo(
    val paymentList: List<TabbyPaymentItem>,
    val workDesc: List<String>
)


data class TabbyPaymentItem(
    val paymentCount: Int,
    val fees: Double,
    val amount: Double)