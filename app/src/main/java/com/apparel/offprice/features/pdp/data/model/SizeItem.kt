package com.apparel.offprice.features.pdp.data.model


data class SizeItem(
    val label: String,
    val stock: Int,
    val disabled: Boolean = false
)