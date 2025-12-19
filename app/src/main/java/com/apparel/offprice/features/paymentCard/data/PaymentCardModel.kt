package com.apparel.offprice.features.paymentCard.data

data class PaymentCardModel(
    val id: Int,
    val cardNumber: String,
    val cardType: String,
    val expiryDate: String,
    val cvv: String,
    val isDefault: Boolean,
)


val sampleCardList = listOf(
    PaymentCardModel(
        id = 1,
        cardNumber = "1234 5678 9012 3456",
        cardType = "Credit Card",
        expiryDate = "12/25",
        cvv = "123",
        isDefault = true
    ),
    PaymentCardModel(
        id = 2,
        cardNumber = "1234 5678 7875 2453",
        cardType = "Debit Card",
        expiryDate = "09/28",
        cvv = "765",
        isDefault = false
    ),
)
