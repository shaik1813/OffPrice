package com.apparel.offprice.features.home.presentation.screens.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.features.home.data.model.CategoryItem
import com.apparel.offprice.features.home.data.model.DrawerMode
import com.apparel.offprice.features.home.data.model.bottomNavItems
import com.apparel.offprice.features.home.data.model.sampleTopTabs
import com.apparel.offprice.features.home.presentation.screens.categoriesDrawer.CategoriesDrawer
import com.apparel.offprice.features.plp.presentation.screens.PLPScreen
import com.apparel.offprice.features.profile.presentation.myaccounts.MyAccountScreen
import com.apparel.offprice.routes.AppScreen
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    outerNavControl: NavHostController
) {

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    var isCategoriesOpen by remember { mutableStateOf(false) }
    val bottomNavHeight = 100.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val drawerHeight = screenHeight - bottomNavHeight
    val drawerWidth = (LocalConfiguration.current.screenWidthDp / 1.3f).dp
    var drawerMode by remember { mutableStateOf(DrawerMode.CATEGORY_LIST) }
    var selectedCategory by remember { mutableStateOf<CategoryItem?>(null) }
    var selectedTopTab by remember { mutableStateOf(sampleTopTabs.first().id) }



    Column(modifier = Modifier.fillMaxSize()) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(
                    onClose = {
                        scope.launch { drawerState.close() }
                    }
                )
            }
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = MaterialTheme.colorScheme.surface,
                bottomBar = {
                    BottomBar(
                        navController = navController,
                        openNavigationDrawer = { scope.launch { drawerState.open() } },
                    )
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
                    composable("CATEGORIES") { }
                    composable("BESTPRICE") {
                        PLPScreen(
                            onNavigateToSearch = {
                                outerNavControl.navigate(AppScreen.SearchScreen){}
                            },
                            onNavigateToWishlist = {
                                outerNavControl.navigate(AppScreen.WishListScreen){}
                            }
                        )
                    }
                    composable("CART") { Greeting("Cart") }
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

        // MAIN CONTENT (BLUR ONLY THIS)
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//                .then(if (isCategoriesOpen) Modifier.blur(15.dp) else Modifier)
//        ) {
//
//
//        }
    }

    // OVERLAY (SCRIM + DRAWER)
    Box(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        if (isCategoriesOpen) {
            // SCRIM ONLY ABOVE BOTTOM NAVIGATION
            Box(
                modifier = Modifier
                    .height(drawerHeight)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.35f))
                    .align(Alignment.TopStart)
                    .clickable { isCategoriesOpen = false }
            )

            AnimatedVisibility(
                visible = isCategoriesOpen,
                enter = slideInHorizontally(animationSpec = tween(300)) { -it },
                exit = slideOutHorizontally(animationSpec = tween(250)) { -it },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Box(
                    modifier = Modifier
                        .height(drawerHeight)     // drawer stops above bottom nav
                        .width(drawerWidth)
                        .background(Color.White)
                ) {
                    CategoriesDrawer(
                        drawerMode = drawerMode,
                        selectedCategory = selectedCategory,
                        selectedTopTab = selectedTopTab,
                        onTopTabClick = { id -> selectedTopTab = id },
                        onCategoryClick = { category ->
                            selectedCategory = category
                            drawerMode = DrawerMode.SUBCATEGORY_LIST
                        },
                        onSubCategoryClick = { subCategory ->
                            Toast.makeText(
                                context,
                                subCategory.title,
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onBack = {
                            drawerMode = DrawerMode.CATEGORY_LIST
                        },
                        onClose = {
                            isCategoriesOpen = false
                            drawerMode = DrawerMode.CATEGORY_LIST
//                            selectedTab = lastNavigatedTab
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
    openNavigationDrawer: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar{
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    when (item.route) {
                        "CATEGORIES" -> {
                            openNavigationDrawer()
                        }

                        else -> {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
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

@Composable
fun DrawerContent(onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Text("Drawer Item 1", modifier = Modifier.padding(8.dp))
        Text("Drawer Item 2", modifier = Modifier.padding(8.dp))

        Button(onClick = onClose, modifier = Modifier.padding(top = 16.dp)) {
            Text("Close Drawer")
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