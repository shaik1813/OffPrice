package com.apparel.offprice.features.storeLocator.data

import com.apparel.offprice.R

data class StoreListModel(
    val id: String,
    val name: String,
    val phone: String,
    val location: String,
    val distanceKm: String,
    val openTime: String,
    val imageRes: Int,
    val latitude : Double,
    val longitude : Double
)

data class CountryStoreList(
    val id: String,
    val country: String,
    val storeList: List<StoreListModel>
)

val sampleStoreList = listOf(
    StoreListModel(
        id = "1",
        name = "Ramili Mall",
        phone = "+966541350586",
        location = "bahrain- a’ali",
        distanceKm = "3424.29 KM",
        openTime = "Open Until 10:00 AM",
        imageRes = R.drawable.image_men,
        latitude = 37.7749,
        longitude = -122.419
    ),
    StoreListModel(
        id = "2",
        name = "Aahsa mall",
        phone = "+966541350586",
        location = "bahrain- a’ali",
        distanceKm = "34.29 KM",
        openTime = "Open Until 10:00 AM",
        imageRes = R.drawable.mall_3,
        latitude = 37.7749,
        longitude = -122.419
    ),
    StoreListModel(
        id = "3",
        name = "World Trade Center",
        phone = "+966541350586",
        location = "bahrain- a’ali",
        distanceKm = "256.29 KM",
        openTime = "Open Until 10:00 AM",
        imageRes = R.drawable.image_men,
        latitude = 37.7749,
        longitude = -122.419
    ),
    StoreListModel(
        id = "4",
        name = "City Centre Bahrain",
        phone = "+966541350586",
        location = "bahrain- a’ali",
        distanceKm = "66.25 KM",
        openTime = "Open Until 10:00 AM",
        imageRes = R.drawable.image_men,
        latitude = 37.7749,
        longitude = -122.419
    ),
)

val sampleCountryStoreList = listOf(
    CountryStoreList(
        id = "1",
        country = "Bahrain",
        storeList = sampleStoreList
    ),
    CountryStoreList(
        id = "2",
        country = "Saudi Arabia",
        storeList = sampleStoreList.take(2)
    ),
    CountryStoreList(
        id = "3",
        country = "Qatar",
        storeList = sampleStoreList.take(3)
    ),
    CountryStoreList(
        id = "4",
        country = "Kuwait",
        storeList = sampleStoreList
    ),
    CountryStoreList(
        id = "5",
        country = "UAE",
        storeList = sampleStoreList
    )
)



