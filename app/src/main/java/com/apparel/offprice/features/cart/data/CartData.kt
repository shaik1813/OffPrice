package com.apparel.offprice.features.cart.data

data class CreditItem(
    val points: String,
    val amount: String
)

data class Creditsdata(
    var clubApparelpoint: CreditItem,
    var storePoint : CreditItem
)

data class PriceData(
    var quantity : Int,
    var subTotal: Double,
    var discount : Double,
    var shipping_fee : Double,
    var total : Double,
    var caPointAmount : Double,
    var storePointAmount: Double,
    var grandTotal: Double,
    var points:String
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


var priceData = PriceData(
    quantity = 3,
    subTotal = 105.00,
    discount = 12.00,
    shipping_fee = 14.00,
    total = 107.00,
    caPointAmount = 05.00,
    storePointAmount = 05.00,
    grandTotal = 97.00,
    points = "1000"
)