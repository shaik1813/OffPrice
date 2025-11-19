package com.apparel.offprice.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavigation(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    CATEGORIES("Categories", Icons.Default.Menu),
    BESTPRICE("Best Price", Icons.Default.Star),
    CART("Cart", Icons.Default.ShoppingCart),
    ACCOUNT("Account", Icons.Default.AccountCircle)
}