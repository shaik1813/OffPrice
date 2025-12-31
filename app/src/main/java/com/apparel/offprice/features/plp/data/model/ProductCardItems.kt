package com.apparel.offprice.features.plp.data.model

import com.apparel.offprice.R

data class ProductCardItems(
    val id: String,
    val tag: String?,           // e.g., "GOLD LABEL", "BEST PRICE"
    val tagContainerColor: String,
    val tagContentColor: String,
    val image: List<Int>,
    val brand: String,
    val title: String,
    val sizes: List<String>,
    val basePrice: String,
    val discountPrice: String,
    val rrp: String,
    val discount: String,
    val delivery: String,       // e.g., "GET IT IN 90M" or "DELIVERY BY 06 NOV"
    val deliveryType: String,
    val isWishlist: Boolean = false
)


val sampleProducts = listOf(
    ProductCardItems(
        id = "1",
        tag = "GOLD LABEL",
        tagContainerColor = "#FFB47F00",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_1,
            R.drawable.product_item_2,
            R.drawable.product_item_3,
            R.drawable.product_item_4,
            R.drawable.colorimg,
            R.drawable.colorimg2
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "120.00",
        rrp = "172.00",
        discount = "90%",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
    ProductCardItems(
        id = "2",
        tag = "BEST PRICE",
        tagContainerColor = "#FF93050C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_2,
            R.drawable.product_item_4
        ), // Replace with real image
        brand = "PUMA",
        title = "Men's Running Shoes",
        sizes = listOf("43", "44", "45"),
        basePrice = "161.00",
        discountPrice = "187.00",
        rrp = "220.00",
        discount = "73%",
        delivery = "DELIVERY BY",
        deliveryType = "06 NOV, THU"
    ),
    ProductCardItems(
        id = "3",
        tag = "BESTSELLER",
        tagContainerColor = "#FFED1D2C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_1,
            R.drawable.product_item_2,
            R.drawable.product_item_3,
            R.drawable.product_item_4
        ),
        brand = "NIKE",
        title = "Women's Stylish Top",
        sizes = listOf("S", "M", "L"),
        basePrice = "72.00",
        discountPrice = "",
        rrp = "120.00",
        discount = "40%",
        delivery = "DELIVERY BY",
        deliveryType = "TOMORROW"
    ),
    ProductCardItems(
        id = "4",
        tag = "GOLD LABEL",
        tagContainerColor = "#FFB47F00",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_1,
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
    ProductCardItems(
        id = "5",
        tag = "PAY DAY",
        tagContainerColor = "#FFF4FF19",
        tagContentColor = "#FF000000",
        image = listOf(),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
    ProductCardItems(
        id = "6",
        tag = "NEW",
        tagContainerColor = "#FFFFFFFF",
        tagContentColor = "#FFB5373D",
        image = listOf(
            R.drawable.product_item_3,
            R.drawable.product_item_4
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
)

val sampleBestPriceProducts = listOf(
    ProductCardItems(
        id = "1",
        tag = "BEST PRICE",
        tagContainerColor = "#FF93050C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_1,
            R.drawable.product_item_2,
            R.drawable.product_item_3,
            R.drawable.product_item_4,
            R.drawable.colorimg,
            R.drawable.colorimg2
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "120.00",
        rrp = "172.00",
        discount = "90%",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
    ProductCardItems(
        id = "2",
        tag = "BEST PRICE",
        tagContainerColor = "#FF93050C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_2,
            R.drawable.product_item_4
        ), // Replace with real image
        brand = "PUMA",
        title = "Men's Running Shoes",
        sizes = listOf("43", "44", "45"),
        basePrice = "161.00",
        discountPrice = "187.00",
        rrp = "220.00",
        discount = "73%",
        delivery = "DELIVERY BY",
        deliveryType = "06 NOV, THU"
    ),
    ProductCardItems(
        id = "3",
        tag = "BEST PRICE",
        tagContainerColor = "#FF93050C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_1,
            R.drawable.product_item_2,
            R.drawable.product_item_3,
            R.drawable.product_item_4
        ),
        brand = "NIKE",
        title = "Women's Stylish Top",
        sizes = listOf("S", "M", "L"),
        basePrice = "72.00",
        discountPrice = "",
        rrp = "120.00",
        discount = "40%",
        delivery = "DELIVERY BY",
        deliveryType = "TOMORROW"
    ),
    ProductCardItems(
        id = "4",
        tag = "BEST PRICE",
        tagContainerColor = "#FF93050C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_1,
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
    ProductCardItems(
        id = "5",
        tag = "BEST PRICE",
        tagContainerColor = "#FF93050C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
    ProductCardItems(
        id = "6",
        tag = "BEST PRICE",
        tagContainerColor = "#FF93050C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_3,
            R.drawable.product_item_4
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
    ProductCardItems(
        id = "7",
        tag = "BEST PRICE",
        tagContainerColor = "#FF93050C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_1,
            R.drawable.product_item_3,
            R.drawable.product_item_4
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
    ProductCardItems(
        id = "8",
        tag = "BEST PRICE",
        tagContainerColor = "#FF93050C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_1,
            R.drawable.product_item_4
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
    ProductCardItems(
        id = "9",
        tag = "BEST PRICE",
        tagContainerColor = "#FF93050C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_3,
            R.drawable.product_item_4
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
    ProductCardItems(
        id = "10",
        tag = "BEST PRICE",
        tagContainerColor = "#FF93050C",
        tagContentColor = "#FFFFFFFF",
        image = listOf(
            R.drawable.product_item_2,
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT",
        deliveryType = "IN 90MINS"
    ),
)
