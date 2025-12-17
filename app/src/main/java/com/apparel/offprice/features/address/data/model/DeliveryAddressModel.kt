package com.apparel.offprice.features.address.data.model

import com.apparel.offprice.features.home.data.model.Country

data class DeliveryAddressModel(
    val id: Int,
    val tag: DeliveryAddressFilter,
    val userName: String,
    val address: String,
    val phone: String,
    val countryCode: Country,
    val city: String,
    val area: String,
    val isDefault: Boolean
)


enum class DeliveryAddressFilter {
    HOME,WORK,OTHER
}

