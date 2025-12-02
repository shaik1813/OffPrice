package com.apparel.offprice.features.home.data.model

import com.apparel.offprice.R

data class BottomNavItem(
    val route: String,
    val label: String,
    val badgeCount: Int = 0,
    val filledIcon: Int,
    val outlinedIcon: Int
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = "HOME",
        label = "Home",
        badgeCount = 0,
        filledIcon = R.drawable.home_fill_icon,
        outlinedIcon = R.drawable.home_outlined_icon
    ),
    BottomNavItem(
        route = "CATEGORIES",
        label = "Categories",
        badgeCount = 0,
        filledIcon = R.drawable.category_fill_icon,
        outlinedIcon = R.drawable.category_outline_icon
    ),
    BottomNavItem(
        route = "BESTPRICE",
        label = "Best Price",
        badgeCount = 0,
        filledIcon = R.drawable.best_price_fill_icon,
        outlinedIcon = R.drawable.best_price_outline_icon
    ),
    BottomNavItem(
        route = "CART",
        label = "Cart",
        badgeCount = 3,
        filledIcon = R.drawable.bag_cart_filled_icon,
        outlinedIcon = R.drawable.bag_cart_outline_icon
    ),
    BottomNavItem(
        route = "ACCOUNT",
        label = "Account",
        badgeCount = 0,
        filledIcon = R.drawable.account_fill_icon,
        outlinedIcon = R.drawable.account_outline_icon
    )
)