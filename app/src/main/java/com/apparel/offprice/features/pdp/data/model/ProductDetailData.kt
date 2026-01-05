package com.apparel.offprice.features.pdp.data.model

import com.apparel.offprice.features.plp.presentation.screens.ProductCardItems
import com.apparel.offprice.R

val productList : List<ProductCardItems> = listOf(
    ProductCardItems(
        id = "1",
        tag = "GOLD LABEL",
        tagContainerColor = "#FFB47F00",
        tagContentColor = "#FFFFFFFF",
        image = listOf(R.drawable.product_item_1),
        brand = "Nike",
        title = "Nike Air Max 270 React ENG",
        sizes = listOf("UK 6", "UK 7", "UK 8", "UK 9"),
        basePrice = "139.99",
        discountPrice = "",
        rrp = "159.99",
        discount = "20",
        delivery = "GET IT IN 90M",
        isWishlist = false
    )
,
    ProductCardItems(
        id = "1",
        tag = "GOLD LABEL",
        tagContainerColor = "#FFB47F00",
        tagContentColor = "#FFFFFFFF",
        image = listOf(R.drawable.product_item_1),
        brand = "Nike",
        title = "Nike Air Max 270 React ENG",
        sizes = listOf("UK 6", "UK 7", "UK 8", "UK 9"),
        basePrice = "139.99",
        discountPrice = "",
        rrp = "159.99",
        discount = "20",
        delivery = "GET IT IN 90M",
        isWishlist = false
    ),
    ProductCardItems(
        id = "1",
        tag = "GOLD LABEL",
        tagContainerColor = "#FFB47F00",
        tagContentColor = "#FFFFFFFF",
        image = listOf(R.drawable.product_item_1),
        brand = "Nike",
        title = "Nike Air Max 270 React ENG",
        sizes = listOf("UK 6", "UK 7", "UK 8", "UK 9"),
        basePrice = "139.99",
        discountPrice = "",
        rrp = "159.99",
        discount = "20",
        delivery = "GET IT IN 90M",
        isWishlist = false
    ),
    ProductCardItems(
        id = "1",
        tag = "GOLD LABEL",
        tagContainerColor = "#FFB47F00",
        tagContentColor = "#FFFFFFFF",
        image = listOf(R.drawable.product_item_1),
        brand = "Nike",
        title = "Nike Air Max 270 React ENG",
        sizes = listOf("UK 6", "UK 7", "UK 8", "UK 9"),
        basePrice = "139.99",
        discountPrice = "",
        rrp = "159.99",
        discount = "20",
        delivery = "GET IT IN 90M",
        isWishlist = false
    ),
    ProductCardItems(
        id = "1",
        tag = "GOLD LABEL",
        tagContainerColor = "#FFB47F00",
        tagContentColor = "#FFFFFFFF",
        image = listOf(R.drawable.icon_user_profile),
        brand = "Nike",
        title = "Nike Air Max 270 React ENG",
        sizes = listOf("UK 6", "UK 7", "UK 8", "UK 9"),
        basePrice = "139.99",
        discountPrice = "",
        rrp = "159.99",
        discount = "20",
        delivery = "GET IT IN 90M",
        isWishlist = false
    ))

val pdpDetail = ProductDetailItem(
    id = "1",
    tag = "GOLD LABEL",
    tagContainerColor = "#FFB47F00",
    tagContentColor = "#FFFFFFFF",
    image = listOf(
        R.drawable.product_item_large1,
        R.drawable.product_item_large2,
        R.drawable.product_item_large3,
        R.drawable.product_item_large2,
        R.drawable.product_item_large1,
        R.drawable.product_item_large3
    ),  // Replace with real image
    brand = "ADIDAS",
    title = "Printed Shirt with Crew Neck Member with Polish Style",
    colorImages =  listOf(
        R.drawable.product_item_1,
        R.drawable.colorimg,
        R.drawable.colorimg2,
        R.drawable.product_item_3
    ),
    sizes = listOf("S", "M", "L", "XL"),
    basePrice = "35.00",
    discountPrice = "120.00",
    rrp = "172.00",
    discount = "90%",
    delivery = "GET IT IN 90MINS"
)


val tabbyPaymentDetail = TabbyPaymentInfo(
    listOf(TabbyPaymentItem(4,0.0,29.75),
        TabbyPaymentItem(6,5.95,20.83),
        TabbyPaymentItem(8,10.71,16.21),
        TabbyPaymentItem(12,20.23,11.60)),
    listOf("Choose Tabby at checkout to select a payment plan",
        "Enter your information and add your debit and credit card",
        "Depending on your plan, you may or may not make down payment",
        "We'll send you reminder when you next payment is due"))