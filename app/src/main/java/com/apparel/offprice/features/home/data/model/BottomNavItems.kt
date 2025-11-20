package com.apparel.offprice.features.home.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import com.apparel.offprice.R

data class BottomNavItem(
    val route: String,
    val label: String,
    val filledIcon: Int,
    val outlinedIcon: Int
)

val bottomNavItems = listOf(
    BottomNavItem("HOME", "Home", R.drawable.home_fill_icon, R.drawable.home_outlined_icon),
    BottomNavItem("CATEGORIES", "Categories", R.drawable.category_fill_icon, R.drawable.category_outline_icon),
    BottomNavItem("BESTPRICE", "Best Price", R.drawable.best_price_fill_icon, R.drawable.best_price_outline_icon),
    BottomNavItem("CART", "Cart", R.drawable.bag_cart_outline_icon, R.drawable.bag_cart_outline_icon),
    BottomNavItem("ACCOUNT", "Account", R.drawable.account_fill_icon, R.drawable.account_outline_icon)
)