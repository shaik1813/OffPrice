package com.apparel.offprice.features.home.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.features.cart.presentation.screen.CartScreen
import com.apparel.offprice.features.home.data.model.bottomNavItems
import com.apparel.offprice.features.plp.presentation.screens.PLPScreen
import com.apparel.offprice.features.profile.presentation.screen.myaccounts.MyAccountScreen
import com.apparel.offprice.routes.AppScreen

@Composable
fun HomeScreen(outerNavControl: NavHostController) {

    val navController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.surface,
            bottomBar = {
                BottomBar(navController = navController)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "HOME",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("HOME") {
                    HomeContent(
                        onNavigateToSearch = {
                            outerNavControl.navigate(AppScreen.SearchScreen) {}
                        },
                        onNavigateToStore = {
                            outerNavControl.navigate(AppScreen.StoreLocatorScreen) {}
                        },
                        onNavigateToWishlist = {
                            outerNavControl.navigate(AppScreen.WishListScreen) {}
                        }
                    )
                }
                composable("CATEGORIES") {Greeting("Categories") }
                composable("BESTPRICE") {
                    PLPScreen(
                        onNavigateToSearch = {
                            outerNavControl.navigate(AppScreen.SearchScreen) {}
                        },
                        onNavigateToWishlist = {
                            outerNavControl.navigate(AppScreen.WishListScreen) {}
                        }
                    )
                }
                composable("CART") { CartScreen() }

                composable("ACCOUNT") {
                    MyAccountScreen(
                        isGuestUser = false,
                        onNavigateToSearch = {
                            outerNavControl.navigate(AppScreen.SearchScreen) {}
                        },
                        onNavigateToWishlist = {
                            outerNavControl.navigate(AppScreen.WishListScreen) {}
                        },
                        onItemClick = { item ->
                            when (item.categoryId) {
                                0 -> {
                                    //LogOut
                                }

                                1 -> {
                                    //User Profile
                                    outerNavControl.navigate(AppScreen.UserProfileScreen) {}
                                }

                                2 -> {
                                    //My Orders
                                }

                                3 -> {
                                    //Returns
                                }

                                4 -> {
                                    //Exchange
                                }

                                5 -> {
                                    //Store Credit
                                }

                                6 -> {
                                    //MyCoupons
                                }

                                7 -> {
                                    //Delivery Address
                                }

                                8 -> {
                                    //Payment cards
                                }

                                9 -> {
                                    //Store Locator
                                }

                                else -> {
                                    //Other case
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(string: String) {
    Text(text = string)
}

@Composable
fun BottomBar(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    val iconRes =
                        if (currentRoute == item.route) item.filledIcon
                        else item.outlinedIcon
                    if (item.badgeCount > 0) {
                        BadgedBox(badge = {
                            Badge { Text("${item.badgeCount}") }
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
                    Text(
                        text = item.label,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}


@Preview
@Composable
fun CategoryPreview() {
    HomeScreen(
        outerNavControl = rememberNavController()
    )
}