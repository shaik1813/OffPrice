package com.apparel.offprice.features.myorder.data

import com.apparel.offprice.features.cart.data.CartProductItems
import com.apparel.offprice.features.plp.data.model.ProductCardItems

data class OrderItem(
    val orderId: String,
    val orderStatus: String,
    val items:List<CartProductItems>
)


