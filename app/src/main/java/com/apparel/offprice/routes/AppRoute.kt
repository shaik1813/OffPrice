package com.apparel.offprice.routes

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.features.home.presentation.screens.home.HomeScreen
import com.apparel.offprice.features.home.presentation.screens.search.SearchScreen
import com.apparel.offprice.features.storeLocator.presentation.screen.StoreLocatorScreen
import com.apparel.offprice.features.wishlist.presentation.screen.WishListScreen


@Composable
fun AppRoutes(windowSizeClass: WindowSizeClass) {

    val navController = rememberNavController()

    val widthSizeClass = windowSizeClass.widthSizeClass
    val heightSizeClass = windowSizeClass.heightSizeClass

    NavHost(
        navController = navController,
        startDestination = AppScreen.HomeScreen
    ){
        composable<AppScreen.SplashScreen> {

        }

        composable<AppScreen.LoginScreen> {

        }

        composable<AppScreen.RegistrationScreen> {

        }

        composable< AppScreen.ForgetPasswordScreen> {

        }

        composable< AppScreen.OTPScreen> {

        }

        composable< AppScreen.HomeScreen> {
            HomeScreen(outerNavControl = navController)
        }

        composable <AppScreen.SearchScreen>{
            SearchScreen(
                onSearchSubmit = { productId ->
                    // TODO : Added the PLP navigation from here
                },
                onNavigateToHome = {
                    navController.navigate(AppScreen.HomeScreen){
                        popUpTo (AppScreen.HomeScreen){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<AppScreen.StoreLocatorScreen> {
            StoreLocatorScreen()
        }

        composable<AppScreen.WishListScreen> {
            WishListScreen()
        }

    }

}