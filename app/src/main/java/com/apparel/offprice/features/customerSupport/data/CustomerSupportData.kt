package com.apparel.offprice.features.customerSupport.data

import com.apparel.offprice.R
import com.apparel.offprice.features.profile.data.MyAccountItems
import com.apparel.offprice.routes.AppScreen


val customerSupportItems = listOf(
    MyAccountItems(R.string.label_about_us, R.drawable.icon_info,11, AppScreen.AboutUsScreen),
    MyAccountItems(R.string.label_disclaimer, R.drawable.icon_disclaimer,12 , AppScreen.DisclaimerScreen),
    MyAccountItems(R.string.label_contact_inquiry, R.drawable.icon_contact_inquiry,13 , AppScreen.ContactInquiryScreen),
    MyAccountItems(R.string.label_return_policy, R.drawable.icon_returns,14 , AppScreen.ReturnPolicyScreen),
    MyAccountItems(R.string.label_terms_and_condition, R.drawable.icon_terms_and_condition,15 , AppScreen.TermsAndConditionScreen),
    MyAccountItems(R.string.label_shipping_info, R.drawable.icon_shipping_info,16 , AppScreen.ShippingInfoScreen),
    MyAccountItems(R.string.label_privacy_policy, R.drawable.icon_privacy_policy,17 , AppScreen.PrivacyPolicyScreen),
)
data class Brand(
    val name: String,
    val imageRes: Int
)

val sampleBrandList = listOf(
    Brand("ZARA", R.drawable.brand_zara),
    Brand("GUCCI", R.drawable.brand_gucci),
    Brand("NIKE", R.drawable.brand_zara),
    Brand("LOUIS VUITTON", R.drawable.brand_zara),
    Brand("H&M", R.drawable.brand_zara),
    Brand("ADIDAS", R.drawable.brand_zara),
    Brand("PUMA", R.drawable.brand_zara),
    Brand("MANGO", R.drawable.brand_zara)
)


data class FeatureCardModel(
    val title : String,
    val description : String,
    val imageIcon : Int,
)


val sampleFeatureList = listOf(
    FeatureCardModel(
        title = "ALWAYS ON TREND\nALWAYS OFFPRICE",
        description = "1500+ international brands to browse",
        imageIcon = R.drawable.icon_trent_about_us
    ),
    FeatureCardModel(
        title = "Trends from around the world",
        description = "Not feeling your purchase?",
        imageIcon = R.drawable.icon_about_us_world
    ),
    FeatureCardModel(
        title = "100% ORIGINAL CERTIFIED BY BRANDS",
        description = "",
        imageIcon = R.drawable.icon_about_us_medal_star
    ),
    FeatureCardModel(
        title = "Spend more & redeem!",
        description = "Earn points by linking your Club Apparel account",
        imageIcon = R.drawable.icon_about_us_free_ship
    ),
)