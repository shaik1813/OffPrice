package com.apparel.offprice.features.pdp.data.model

data class TamaraPaymentInfo(
    val paymentList: List<TamaraPaymentItem>,
    val workDesc: List<TamaraWorkItem>
)


data class TamaraPaymentItem(
    val paymentCount: Int,
    val fees: Double,
    val amount: Double)

data class TamaraWorkItem(
    val title: String,
    val desc: String)