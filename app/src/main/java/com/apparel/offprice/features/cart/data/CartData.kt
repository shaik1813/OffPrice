package com.apparel.offprice.features.cart.data

import com.apparel.offprice.R

data class CreditItem(
    val points: String,
    val amount: Double
)

data class Creditsdata(
    var clubApparelpoint: CreditItem,
    var storePoint: CreditItem
)

data class PriceData(
    var quantity: Int,
    var subTotal: Double,
    var discount: Double,
    var shipping_fee: Double,
    var total: Double,
    var caPointAmount: Double,
    var storePointAmount: Double,
    var grandTotal: Double,
    var points: String
)

var creditsData = Creditsdata(
    clubApparelpoint = CreditItem(
        points = "1000 Points",
        amount = 05.00
    ),
    storePoint = CreditItem(
        points = "",
        amount = 05.00
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

var offerList = listOf(
    "This coupon is valid for customers within the UAE only.",
    "Minimum purchase value must be AED 25.",
    "Offer cannot be combined with other promotions or discount codes.",
    "The brand reserves the right to amend or cancel the offer without prior notice."
)


data class CartProductItems(
    val id: Int,
    val tag: String?,
    val image: Int,
    val brand: String,
    val title: String,
    val color: String,
    val size: String,
    var quantity: Int,
    val basePrice: Double,
    val discountPrice: Double,
    val rrp: Double,
    val discount: String,
    val delivery: String,
    val isWishlist: Boolean = false,
    val isCouponApply: Int

)


val cartProducts = listOf(
    CartProductItems(
        id = 1,
        tag = "GOLD LABEL",
        image = R.drawable.product_item_1,  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        color = "Blue",
        size = "L",
        quantity = 1,
        basePrice = 35.00,
        discountPrice = 120.00,
        rrp = 172.00,
        discount = "90%",
        delivery = "DELIVERY BY 06 NOV, THU",
        isCouponApply = 2
    ),
    CartProductItems(
        id = 2,
        tag = "GOLD LABEL",
        image = R.drawable.product_item_2,
        brand = "NIKE",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        color = "Blue",
        size = "EU 42",
        quantity = 1,
        basePrice = 35.00,
        discountPrice = 120.00,
        rrp = 172.00,
        discount = "90%",
        delivery = "GET IT IN 90 MINS",
        isCouponApply = 1
    ),
    CartProductItems(
        id = 3,
        tag = "GOLD LABEL",
        image = R.drawable.product_item_2,
        brand = "PUMA",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        color = "Blue",
        size = "L",
        quantity = 1,
        basePrice = 35.00,
        discountPrice = 120.00,
        rrp = 172.00,
        discount = "90%",
        delivery = "GET IT TODAY",
        isCouponApply = 0
    )
)