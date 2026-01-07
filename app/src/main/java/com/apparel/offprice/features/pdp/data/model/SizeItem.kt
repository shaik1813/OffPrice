package com.apparel.offprice.features.pdp.data.model


data class SizeItem(
    val id : String,
    val label: String,
    val stock: Int,
    val disabled: Boolean = false
)

enum class ShoeType {
    EU, UK, US
}

data class ShoeSizeType(
    val type: ShoeType,
    val sizeList: List<SizeItem> = emptyList()
)


val clothSize = listOf(
    SizeItem(id= "1", label = "S", stock = 8),
    SizeItem(id= "2", label = "M", stock = 1),
    SizeItem(id= "3",label = "L", stock = 2),
    SizeItem(id= "4",label = "XL", stock = 0 ),
    SizeItem(id= "5",label = "XXL", stock = 7),
    SizeItem(id= "6",label = "XXXL", stock = 0)
)