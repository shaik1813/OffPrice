package com.apparel.offprice.features.home.data.model

import BottomNavScreen
import com.apparel.offprice.R

data class BottomNavItem(
    val route: BottomNavScreen,
    val label: String,
    val badgeCount: Int = 0,
    val filledIcon: Int,
    val outlinedIcon: Int
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = BottomNavScreen.Item1,
        label = "Home",
        badgeCount = 0,
        filledIcon = R.drawable.home_fill_icon,
        outlinedIcon = R.drawable.home_outlined_icon
    ),
    BottomNavItem(
        route = BottomNavScreen.Item2,
        label = "Categories",
        badgeCount = 0,
        filledIcon = R.drawable.category_fill_icon,
        outlinedIcon = R.drawable.category_outline_icon
    ),
    BottomNavItem(
        route = BottomNavScreen.Item3,
        label = "Best Price",
        badgeCount = 0,
        filledIcon = R.drawable.best_price_fill_icon,
        outlinedIcon = R.drawable.best_price_outline_icon
    ),
    BottomNavItem(
        route = BottomNavScreen.Item4,
        label = "Cart",
        badgeCount = 3,
        filledIcon = R.drawable.bag_cart_filled_icon,
        outlinedIcon = R.drawable.bag_cart_outline_icon
    ),
    BottomNavItem(
        route = BottomNavScreen.Item5,
        label = "Account",
        badgeCount = 0,
        filledIcon = R.drawable.account_fill_icon,
        outlinedIcon = R.drawable.account_outline_icon
    )
)
