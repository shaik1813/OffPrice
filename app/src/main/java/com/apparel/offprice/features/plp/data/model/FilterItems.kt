package com.apparel.offprice.features.plp.data.model


data class FilterItem(
    val id: String,
    val name: String,
    val itemStock: Int = 0,
    val isSelected: Boolean = false,
    val hexColor: String? = null, // Only used for color filter
    val sizeRegion: ShoeSizeRegion? = null
)

data class FilterGroup(
    val type: FilterType,
    val title: String,
    val items: List<FilterItem>,
    val isMultiSelect: Boolean = true
)

enum class FilterType {
    BRAND, CATEGORY, CLOTHING_SIZE, SHOE_SIZE, COLOR, PRICE, DISCOUNT, DELIVERY_TYPE, OCCASION, BY_STOCK
}

enum class ShoeSizeRegion {
    EU, UK, US
}

val filterList = listOf(
    FilterGroup(
        type = FilterType.BRAND,
        title = "Brand",
        items = listOf(
            FilterItem("1", "Buma", itemStock = 20),
            FilterItem("2", "Nike", itemStock = 16),
            FilterItem("3", "Adidas", itemStock = 10),
            FilterItem("4", "Zara", itemStock = 8),
            FilterItem("5", "Gucci", itemStock = 12),
            FilterItem("6", "Maniac", itemStock = 22),
        )
    ),
    FilterGroup(
        type = FilterType.CATEGORY,
        title = "Categories",
        items = listOf(
            FilterItem("1", "T-Shirts", itemStock = 10),
            FilterItem("2", "Shirts", itemStock = 16),
            FilterItem("3", "Polo Shirts", itemStock = 10),
            FilterItem("4", "Pants", itemStock = 8),
            FilterItem("5", "Hoodies & Sweatshirts", itemStock = 15),
            FilterItem("6", "Underwear & Socks", itemStock = 22),
            FilterItem("7", "Shorts", itemStock = 1),
        )
    ),
    FilterGroup(
        type = FilterType.CLOTHING_SIZE,
        title = "Clothing Size",
        items = listOf(
            FilterItem("1", "XS", itemStock = 10),
            FilterItem("2", "S", itemStock = 16),
            FilterItem("3", "M", itemStock = 10),
            FilterItem("4", "L", itemStock = 2),
            FilterItem("5", "XL", itemStock = 15),
            FilterItem("6", "XXL", itemStock = 22),
            FilterItem("7", "XXXL", itemStock = 26),
        )
    ),
    FilterGroup(
        type = FilterType.SHOE_SIZE,
        title = "Shoe Size",
        items = listOf(

            // 🔹 EU
            FilterItem("eu_40", "40", itemStock = 10),
            FilterItem("eu_41", "41", itemStock = 12),
            FilterItem("eu_42", "42", itemStock = 8),
            FilterItem("eu_43", "43", itemStock = 6),

            // 🔹 UK
            FilterItem("uk_6", "6", itemStock = 7),
            FilterItem("uk_7", "7", itemStock = 9),
            FilterItem("uk_8", "8", itemStock = 5),
            FilterItem("uk_9", "9", itemStock = 4),

            // 🔹 US
            FilterItem("us_7", "7", itemStock = 11),
            FilterItem("us_8", "8", itemStock = 13),
            FilterItem("us_9", "9", itemStock = 10),
            FilterItem("us_10", "10", itemStock = 6)
        )
    ),
    FilterGroup(
        type = FilterType.COLOR,
        title = "Color",
        items = listOf(
            FilterItem("1", "Black", itemStock = 10, hexColor = "#000000"),
            FilterItem("2", "White", itemStock = 16, hexColor = "#FFFFFF"),
            FilterItem("3", "Red", itemStock = 10, hexColor = "#FF0000"),
            FilterItem("4", "Blue", itemStock = 2, hexColor = "#0000FF"),
            FilterItem("5", "Green", itemStock = 15, hexColor = "#00FF00"),
            FilterItem("6", "Yellow", itemStock = 22, hexColor = "#FFFF00"),
        )
    ),
    FilterGroup(
        type = FilterType.PRICE,
        title = "Price",
        items = listOf()
    ),
    FilterGroup(
        type = FilterType.DISCOUNT,
        title = "Discount",
        items = listOf(
            FilterItem("1", "Discount Item Only", itemStock = 125),
            FilterItem("2", "Full Price Item Only", itemStock = 66),
        )
    ),
    FilterGroup(
        type = FilterType.DELIVERY_TYPE,
        title = "Delivery Type",
        items = listOf(
            FilterItem("1", "Global Delivery", itemStock = 125),
            FilterItem("2", "Get it Today", itemStock = 66),
            FilterItem("3", "Get it Tomorrow", itemStock = 66),
            FilterItem("4", "Get it 90 MINS", itemStock = 66),
        )
    ),
    FilterGroup(
        type = FilterType.OCCASION,
        title = "Occasion",
        items = listOf(
            FilterItem("1", "Casual", itemStock = 125),
            FilterItem("2", "Street", itemStock = 66),
            FilterItem("3", "Active", itemStock = 26),
            FilterItem("4", "Formal", itemStock = 265),
            FilterItem("5", "Sports", itemStock = 56),
            FilterItem("6", "Vintage", itemStock = 50),
        )
    ),
    FilterGroup(
        type = FilterType.BY_STOCK,
        title = "By Stock",
        items = listOf(
            FilterItem("1", "In Stock", itemStock = 125),
        )
    ),
)


val inlineFilterList = listOf(
    FilterGroup(
        type = FilterType.COLOR,
        title = "Color",
        items = listOf(
            FilterItem("1", "Black", itemStock = 10, hexColor = "#000000"),
            FilterItem("2", "White", itemStock = 16, hexColor = "#FFFFFF"),
            FilterItem("3", "Red", itemStock = 10, hexColor = "#FF0000"),
            FilterItem("4", "Blue", itemStock = 2, hexColor = "#0000FF"),
            FilterItem("5", "Green", itemStock = 15, hexColor = "#00FF00"),
            FilterItem("6", "Yellow", itemStock = 22, hexColor = "#FFFF00"),
            FilterItem("7", "Maroon", itemStock = 22, hexColor = "#FFDF45"),
            FilterItem("8", "Pink", itemStock = 22, hexColor = "#FF7800"),
            FilterItem("9", "Yellow", itemStock = 22, hexColor = "#FFFF00"),
            FilterItem("10", "Yellow", itemStock = 22, hexColor = "#FFFF00"),
            FilterItem("11", "Yellow", itemStock = 22, hexColor = "#FFFF00"),
        )
    ),
    FilterGroup(
        type = FilterType.CLOTHING_SIZE,
        title = "Size",
        items = listOf(
            FilterItem("1", "XS", itemStock = 10),
            FilterItem("2", "S", itemStock = 16),
            FilterItem("3", "M", itemStock = 10),
            FilterItem("4", "L", itemStock = 2),
            FilterItem("5", "XL", itemStock = 15),
            FilterItem("6", "XXL", itemStock = 22),
            FilterItem("7", "XXXL", itemStock = 26),
        )
    ),
    FilterGroup(
        type = FilterType.PRICE,
        title = "Price",
        items = listOf()
    )

)
