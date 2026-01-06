package com.apparel.offprice.features.checkout.presentation.components

import com.apparel.offprice.R
import com.apparel.offprice.features.cart.data.PriceData

data class ProductItem(
    val id: String,
    val brand: String,
    val title: String,
    val image: Int,
    val color: String,
    val size: String,
    val qty: Int,
    val price: String,
    val mrp: String,
    val discountText: String,
    val deliveryText: String
)

data class PriceData(
    val quantity: Int,
    val subTotal: Double,
    val discount: Double,
    val shippingFee: Double,
    val total: Double,
    val caPointAmount: Double,
    val storePointAmount: Double,
    val grandTotal: Double,
    val points: String,
    var isAutoCoupon : Boolean
)


val priceData = PriceData(
    quantity = 3,
    subTotal = 105.00,
    discount = 12.00,
    shippingFee = 14.00,
    total = 107.00,
    caPointAmount = 05.00,
    storePointAmount = 05.00,
    grandTotal = 97.00,
    points = "1000",
    isAutoCoupon = false
)

val sampleProductsOSS = listOf(
    ProductItem(
        id = "1",
        brand = "ZARA",
        title = "Printed Shirt With Crew Neck And Short Sleeves",
        image = R.drawable.product_item_1,
        color = "Blue",
        size = "L",
        qty = 1,
        price = "35.00",
        mrp = "172.00",
        discountText = "90% OFF",
        deliveryText = "DELIVERY BY 06 NOV, THU"
    ),
    ProductItem(
        id = "2",
        brand = "NIKE",
        title = "Printed Shirt With Crew Neck And Short Sleeves",
        image = R.drawable.product_item_2,
        color = "Blue",
        size = "L",
        qty = 1,
        price = "35.00",
        mrp = "172.00",
        discountText = "90% OFF",
        deliveryText = "GET IT TODAY"
    ),
    ProductItem(
        id = "1",
        brand = "MAXX",
        title = "Printed Shirt With Crew Neck And Short Sleeves",
        image = R.drawable.product_item_3,
        color = "Blue",
        size = "L",
        qty = 1,
        price = "35.00",
        mrp = "172.00",
        discountText = "90% OFF",
        deliveryText = "DELIVERY BY 06 NOV, THU"
    )
)


enum class ShippingAddressFilter {
    DELIVERY, PICKUPATSTORE
}

enum class CheckoutStep {
    ADDRESS,
    SUMMARY,
    PAYMENT
}

enum class AddAddressFilter {
    HOME, OFFICE, OTHER
}

enum class AddressSheetMode {
    ADD,
    EDIT
}

enum class PaymentMethod {
    SAVED,
    CARD,
    TAMARA,
    TABBY,
    GOOGLE_PAY,
    COD
}


sealed interface PaymentResult {
    data class Success(
        val orderId: String
    ) : PaymentResult

    data class Failure(
        val orderId: String
    ) : PaymentResult
}

data class AddressUiModel(
    val id: String,
    val label: String,          // Home / Office
    val name: String,           // Hazel / Sheikh Mohammed
    val addressLine: String,    // Flat 402, Al Zahra Building...
    val phone: String
) {
    val shortText: String
        get() = "$label · $addressLine"
}
val sampleAddresses = listOf(
    AddressUiModel(
        id = "1",
        label = "Home",
        name = "Hazel",
        addressLine = "Flat 402, Al Zahra Building Al Nahda Street, Dubai, UAE",
        phone = "+971 436842594"
    ),
    AddressUiModel(
        id = "2",
        label = "Office",
        name = "Sheikh Mohammed",
        addressLine = "Flat 704, Al Hafsa Building Al Nahda Street, Dubai, UAE",
        phone = "+971 436842594"
    )
)

data class AddAddressUiState(
    val selectedType: AddAddressFilter = AddAddressFilter.HOME,
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val address: String = "",
    val city: String = "",
    val area: String = "",
    val isDefault: Boolean = false,
    val isLoading: Boolean = false
)