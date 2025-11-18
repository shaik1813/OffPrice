package com.apparel.offprice.features.home.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.routes.BottomNavigation

@Composable
fun HomeScreen() {

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            BottomNavigation.entries.forEach{ destinations ->
                item(
                    icon = { Icon(
                        destinations.icon,
                        contentDescription = destinations.label)
                    },
                    label = { Text(destinations.label) },
                    selected = currentRoute == destinations.name,
                    onClick = {
                        if (currentRoute != destinations.name) {
                            navController.navigate(destinations.name) {
                                launchSingleTop = true
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    )
    {
        Scaffold(modifier = Modifier.fillMaxSize()){
                innerPadding ->
            NavHost(
                navController = navController,
                startDestination = BottomNavigation.HOME.name,
                modifier = Modifier.padding(innerPadding)
            ){
                composable(BottomNavigation.HOME.name) {
                    Greeting("Home")
                }
                composable(BottomNavigation.CATEGORIES.name) {
                    Greeting("Categories")
                }
                composable(BottomNavigation.BESTPRICE.name) {
                    Greeting("BestPrice")
                }
                composable(BottomNavigation.CART.name) {
                    Greeting("Cart")
                }
                composable(BottomNavigation.ACCOUNT.name) {
                    Greeting("Account")
                }
            }
        }
    }


}

@Composable
fun Greeting(string: String) {
    Text(text = string)
}