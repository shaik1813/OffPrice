package com.apparel.offprice.features.returnFlow.data

import com.apparel.offprice.R
import com.apparel.offprice.features.address.data.model.DeliveryAddressModel

enum class ReturnStatus {
    REQUESTED,
    APPROVED,
    INITIATED
}

data class ReturnItem(
    val returnId: String,
    val brand: String,
    val title: String,
    val imageUrl: String,
    val image: Int,
    val color: String,
    val size: String,
    val qty: Int,
    val price: String,
    val rrp: String,
    val discount: String,
    val reason: String,
    val status: ReturnStatus
)

enum class ReturnFilter {
    LAST_1_MONTH,
    LAST_2_MONTHS,
    LAST_3_MONTHS
}


fun dummyReturns(): List<ReturnItem> =
    listOf(
        ReturnItem(
            returnId = "#2966605041211680",
            brand = "ADIDAS",
            title = "Printed Shirt With Crew Neck",
            imageUrl = "",
            image = R.drawable.product_item_1,
            color = "Blue",
            size = "L",
            qty = 1,
            price = "35.00",
            rrp = "172.00",
            discount = "90% OFF",
            reason = "Received Wrong Item",
            status = ReturnStatus.REQUESTED
        ),
        ReturnItem(
            returnId = "#2966605041211679",
            brand = "PUMA",
            title = "Printed Shirt With Crew Neck",
            imageUrl = "",
            image = R.drawable.product_item_2,
            color = "Blue",
            size = "L",
            qty = 1,
            price = "35.00",
            rrp = "172.00",
            discount = "90% OFF",
            reason = "Received Wrong Item",
            status = ReturnStatus.APPROVED
        ),
        ReturnItem(
            returnId = "#2966605041211678",
            brand = "ZARA",
            title = "Printed Shirt With Crew Neck",
            imageUrl = "",
            image = R.drawable.product_item_3,
            color = "Blue",
            size = "L",
            qty = 1,
            price = "35.00",
            rrp = "172.00",
            discount = "90% OFF",
            reason = "Received Wrong Item",
            status = ReturnStatus.INITIATED
        )
    )


enum class ReturnReason {
    ITEM_DAMAGED_OR_DEFECTIVE,
    SIZE_COLOR_ISSUE,
    ITEM_NOT_AS_DESCRIBED,
    QUALITY_NOT_SATISFACTORY,
    PRODUCT_ARRIVED_LATE,
    CHANGED_MY_MIND,
    FOUND_BETTER_PRICE_ELSEWHERE,
    DUPLICATE_ORDER
}

fun ReturnReason.displayText(): String =
    when (this) {
        ReturnReason.ITEM_DAMAGED_OR_DEFECTIVE -> "Item Damaged or Defective"
        ReturnReason.SIZE_COLOR_ISSUE -> "Size/Color Issue"
        ReturnReason.ITEM_NOT_AS_DESCRIBED -> "Item Not As Described"
        ReturnReason.QUALITY_NOT_SATISFACTORY -> "Quality Not Satisfactory"
        ReturnReason.PRODUCT_ARRIVED_LATE -> "Product Arrived Late"
        ReturnReason.CHANGED_MY_MIND -> "Changed My Mind"
        ReturnReason.FOUND_BETTER_PRICE_ELSEWHERE -> "Found Better Price Elsewhere"
        ReturnReason.DUPLICATE_ORDER -> "Duplicate Order"
    }


enum class ReturnMethod {
    STORE,
    ADDRESS
}

fun ReturnMethod.title(): String =
    when (this) {
        ReturnMethod.STORE -> "Store Return"
        ReturnMethod.ADDRESS -> "Address Return"
    }

fun ReturnMethod.description(): String =
    when (this) {
        ReturnMethod.STORE ->
            "User can return the product at the nearest store for faster processing."
        ReturnMethod.ADDRESS ->
            "Courier partner will collect the product from your address."
    }





data class DeliveryAddressModel(
    val id: String,
    val userName: String,
    val address: String,
    val phone: String,
    val tag: AddressTag,
    val isDefault: Boolean = false
)
enum class AddressTag {
    HOME,
    WORK,
    OTHER
}
fun AddressTag.displayText(): String =
    when (this) {
        AddressTag.HOME -> "Home"
        AddressTag.WORK -> "Work"
        AddressTag.OTHER -> "Other"
    }
val sampleAddresses = listOf(
    DeliveryAddressModel2(
        id = "1",
        userName = "Michael Jonathon",
        address = "Flat 402, Al Zahra Building Al Nahda Street, Al Nahda 2, Dubai, UAE",
        phone = "+971436842594",
        tag = AddressTag.HOME,
        isDefault = true
    ),
    DeliveryAddressModel2(
        id = "2",
        userName = "John Rector",
        address = "Flat 402, Al Zahra Building Al Nahda Street, Al Nahda 2, Dubai, UAE",
        phone = "+971436842594",
        tag = AddressTag.WORK,
        isDefault = false
    )
)

enum class RefundMethod {
    STORE_CREDITS,
    ORIGINAL_PAYMENT
}


data class DeliveryAddressModel2(
    val id: String,
    val userName: String,
    val address: String,
    val phone: String,
    val tag: AddressTag,
    val isDefault: Boolean = false
)