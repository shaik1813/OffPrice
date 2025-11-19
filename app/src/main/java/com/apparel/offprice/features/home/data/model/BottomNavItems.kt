package com.apparel.offprice.features.home.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("HOME", "Home", Icons.Default.Home),
    BottomNavItem("CATEGORIES", "Categories", Icons.Default.Menu),
    BottomNavItem("BESTPRICE", "Best Price", Icons.Default.Star),
    BottomNavItem("CART", "Cart", Icons.Default.ShoppingCart),
    BottomNavItem("ACCOUNT", "Account", Icons.Default.AccountCircle)
)