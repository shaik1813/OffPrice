package com.apparel.offprice.features.home.presentation.screens.home

import BottomNavScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.common.theme.badgeColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.features.cart.presentation.screen.CartScreen
import com.apparel.offprice.features.home.data.model.bottomNavItems
import com.apparel.offprice.features.home.presentation.screens.categories.CategoriesScreen
import com.apparel.offprice.features.plp.presentation.screens.bestPrice.BestPriceScreen
import com.apparel.offprice.features.profile.presentation.screen.myaccounts.MyAccountScreen
import com.apparel.offprice.routes.AppScreen

@Composable
fun HomeScreen(
    onNavigateToOuter: (Any) -> Unit
) {

    val bottomNavController = rememberNavController()
    BottomNavigationContent(
        navController = bottomNavController,
        onNavigateToOuter = {
            onNavigateToOuter(it)
        }
    )


}

@Composable
fun BottomNavigationContent(
    navController: NavHostController,
    onNavigateToOuter: (Any) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavScreen.Item1,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<BottomNavScreen.Item1> {
                HomeContent(
                    onNavigateToSearch = {
                        onNavigateToOuter(AppScreen.SearchScreen)
                    },
                    onNavigateToWishlist = {
                        onNavigateToOuter(AppScreen.WishListScreen)
                    }
                )
            }
            composable<BottomNavScreen.Item2> {
                CategoriesScreen(
                    onNavigateToSearch = {
                        onNavigateToOuter(AppScreen.SearchScreen)
                    },
                    onNavigateToWishlist = {
                        onNavigateToOuter(AppScreen.WishListScreen)
                    },
                    onNavigateToSubCategory = { title ->
                        onNavigateToOuter(AppScreen.SubCategoryScreen(title))
                    }
                )
            }
            composable<BottomNavScreen.Item3> {
                BestPriceScreen(
                    onNavigateToSearch = {
                        onNavigateToOuter(AppScreen.SearchScreen)
                    },
                    onNavigateToWishlist = {
                        onNavigateToOuter(AppScreen.WishListScreen)
                    },
                    onNavigateToPDP = {
                        onNavigateToOuter(AppScreen.PDPScreen(it))
                    }
                )
            }
            composable<BottomNavScreen.Item4> {
                CartScreen(onCheckoutClick = {
                    onNavigateToOuter(AppScreen.ShippingAddressScreen)
                })
            }
            composable<BottomNavScreen.Item5> {
                MyAccountScreen(
                    onNavigateToSearch = {
                        onNavigateToOuter(AppScreen.SearchScreen)
                    },
                    onNavigateToWishlist = {
                        onNavigateToOuter(AppScreen.WishListScreen)
                    },
                    onNavigateToLogin = {
                        onNavigateToOuter(AppScreen.LoginScreen)
                    },
                    onItemClick = { item ->
                        onNavigateToOuter(item)
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: return

    Surface(
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 6.dp
    ) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            bottomNavItems.forEach { item ->
                val selected = currentRoute == item.route::class.qualifiedName
                val iconRes =
                    if (currentRoute == item.route::class.qualifiedName) item.filledIcon
                    else item.outlinedIcon

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        if (item.badgeCount > 0) {
                            BadgedBox(badge = {
                                Badge(
                                    containerColor = badgeColor,
                                    contentColor = Color.White
                                ) { Text("${item.badgeCount}") }
                            }) {
                                Image(
                                    painter = painterResource(id = iconRes),
                                    contentDescription = item.label,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        } else {
                            Image(
                                painter = painterResource(id = iconRes),
                                contentDescription = item.label,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    label = {
                        val fontWeight = if (selected) FontWeight.W700 else FontWeight.W500
                        Text(
                            text = item.label,
                            color = if (selected) MaterialTheme.colorScheme.primary else nonreturnTxtColor,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = fontWeight
                            )
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}
