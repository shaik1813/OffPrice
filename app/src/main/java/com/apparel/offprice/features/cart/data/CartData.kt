package com.apparel.offprice.features.cart.data

data class CreditItem(

    val points: String,
    val amount: String
)

data class Creditsdata(
    var clubApparelpoint: CreditItem,
    var storePoint : CreditItem
)

var creditsData = Creditsdata(
    clubApparelpoint = CreditItem(
       points = "1000 Points",
        amount = "฿ 5.00"
    ),
    storePoint = CreditItem(
        points = "",
        amount = "฿ 5.00"
    )
)