package com.apparel.offprice.features.storeCredit.data

data class StoreCreditTransaction(
    val orderId: String,
    val refundedDate: String,
    val amount: Double,
    val type: CreditType,
)


enum class StoreCreditFilter {
    ALL, RECEIVED, USED
}

enum class CreditType {
    RECEIVED, USED
}