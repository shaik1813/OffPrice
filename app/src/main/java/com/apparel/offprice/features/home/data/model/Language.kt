package com.apparel.offprice.features.home.data.model

data class Language(
    val display : String,
    val localeCode: String,

)

val languageList = listOf(
    Language("ENGLISH","en"),
    Language("ARABIC","ar"),
)
