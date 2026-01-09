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