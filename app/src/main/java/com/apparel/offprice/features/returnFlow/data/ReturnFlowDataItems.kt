package com.apparel.offprice.features.returnFlow.data

import com.apparel.offprice.R

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
            returnId = "#2966605041211678",
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
            returnId = "#2966605041211678",
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
