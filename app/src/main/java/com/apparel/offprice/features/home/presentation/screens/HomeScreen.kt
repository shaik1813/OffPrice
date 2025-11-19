package com.apparel.offprice.features.home.presentation.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.features.home.presentation.screens.categoriesDrawer.CategoriesDrawer
import com.apparel.offprice.features.home.data.model.bottomNavItems

@Composable
fun HomeScreen() {

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    // Drawer State
    var isCategoriesOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            bottomNavItems.forEach{ destinations ->
                item(
                    icon = { Icon(
                        destinations.icon,
                        contentDescription = destinations.label)
                    },
                    label = { Text(destinations.label) },
                    selected = currentRoute == destinations.route,
                    onClick = {
                        if (destinations.route == "CATEGORIES") {
                            // OPEN DRAWER
                            isCategoriesOpen = true
                        } else {
                            // Normal navigation
                            if (currentRoute != destinations.route) {
                                navController.navigate(destinations.route) {
                                    launchSingleTop = true
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    restoreState = true
                                }
                            }
                        }
                    }
                )
            }
        },
        modifier = Modifier
            .then(
                if (isCategoriesOpen) Modifier.blur(15.dp) else Modifier
            )
    )
    {
        Scaffold(modifier = Modifier.fillMaxSize()){
                innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "HOME",
                modifier = Modifier.padding(innerPadding)
            ){
                composable("HOME") {
                    Greeting("Home")
                }
                composable("CATEGORIES") {
                    Greeting("No Categories navigation")
                }
                composable("BESTPRICE") {
                    Greeting("BestPrice")
                }
                composable("CART") {
                    Greeting("Cart")
                }
                composable("ACCOUNT") {
                    Greeting("Account")
                }
            }
        }
    }
        // === OVERLAY + DRAWER ===
        if (isCategoriesOpen) {

            // DARK SCRIM (click anywhere to close)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f))
                    .clickable { isCategoriesOpen = false }
            )

            // LEFT DRAWER (half width)
            val screenWidth = LocalConfiguration.current.screenWidthDp
            val drawerWidth = (screenWidth / 1.3f).dp

            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally { -it },
                exit = slideOutHorizontally { -it }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(drawerWidth)
                        .align(Alignment.CenterStart)
                ) {
                    CategoriesDrawer(
                        onClose = { isCategoriesOpen = false },
                        onItemClick = { categoryItems ->
                            isCategoriesOpen = false
                            //navController.navigate("category_details/$id")
                            Toast.makeText(navController.context, categoryItems.title, Toast.LENGTH_SHORT).show()
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

@Preview
@Composable
fun CategoryPreview(){
    HomeScreen()
}