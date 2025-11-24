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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.features.home.data.model.CategoryItem
import com.apparel.offprice.features.home.data.model.DrawerMode
import com.apparel.offprice.features.home.presentation.screens.categoriesDrawer.CategoriesDrawer
import com.apparel.offprice.features.home.data.model.bottomNavItems
import com.apparel.offprice.features.home.data.model.sampleTopTabs
import com.apparel.offprice.routes.AppScreen

@Composable
fun HomeScreen(
    outerNavControl: NavHostController
) {

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    var selectedTab by remember { mutableStateOf("HOME") }
    var lastNavigatedTab by remember { mutableStateOf("HOME") }
    var isCategoriesOpen by remember { mutableStateOf(false) }
    val bottomNavHeight = 100.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val drawerHeight = screenHeight - bottomNavHeight
    val drawerWidth = (LocalConfiguration.current.screenWidthDp / 1.3f).dp
    var drawerMode by remember { mutableStateOf(DrawerMode.CATEGORY_LIST) }
    var selectedCategory by remember { mutableStateOf<CategoryItem?>(null) }
    var selectedTopTab by remember { mutableStateOf(sampleTopTabs.first().id) }

    Column(modifier = Modifier.fillMaxSize()) {

        // MAIN CONTENT (BLUR ONLY THIS)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)   // ✅ Correct: weight is allowed in Column
                .then(if (isCategoriesOpen) Modifier.blur(15.dp) else Modifier)
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = MaterialTheme.colorScheme.surface
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "HOME",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("HOME") {
                        HomeContent(
                            onNavigateToSearch = {
                                outerNavControl.navigate(AppScreen.SearchScreen){}
                            },
                            onNavigateToStore = {
                                outerNavControl.navigate(AppScreen.WishListScreen){}
                            },
                            onNavigateToWishlist = {
                                outerNavControl.navigate(AppScreen.StoreLocatorScreen){}
                            }
                        )
                    }
                    composable("CATEGORIES") { Greeting("No Categories navigation") }
                    composable("BESTPRICE") { Greeting("BestPrice") }
                    composable("CART") { Greeting("Cart") }
                    composable("ACCOUNT") { Greeting("Account") }
                }
            }
        }

        // BOTTOM NAVIGATION (Always Visible)
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                bottomNavItems.forEach { item ->
                    item(
                        icon = {
                            val iconRes =
                                if (selectedTab == item.route) item.filledIcon
                                else item.outlinedIcon

                            Image(
                                painter = painterResource(id = iconRes),
                                contentDescription = item.label,
                                modifier = Modifier.size(28.dp)
                            )
                        },
                        label = { Text(item.label) },
                        selected = false,
                        onClick = {
                            if (item.route == "CATEGORIES") {
                                selectedTab = "CATEGORIES"
                                    isCategoriesOpen = true
                            } else {
                                // Update both states for real navigation tabs
                                selectedTab = item.route
                                lastNavigatedTab = item.route
                                isCategoriesOpen = false
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                    }
                                }
                            }
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(bottomNavHeight)
        ) {}
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
                            selectedTab = lastNavigatedTab
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
   HomeScreen(
       outerNavControl = rememberNavController()
   )
}