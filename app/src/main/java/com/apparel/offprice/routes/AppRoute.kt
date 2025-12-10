package com.apparel.offprice.routes

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.features.home.presentation.screens.home.HomeScreen
import com.apparel.offprice.features.home.presentation.screens.search.SearchScreen
import com.apparel.offprice.features.pdp.presentation.screen.PDPscreen
import com.apparel.offprice.features.profile.presentation.screen.profileDetails.ProfileDetailsScreen
import com.apparel.offprice.features.profile.presentation.screen.profileSize.ProfileSizeScreen
import com.apparel.offprice.features.profile.presentation.screen.userprofile.UserProfileScreen
import com.apparel.offprice.features.storeLocator.presentation.screen.StoreLocatorScreen
import com.apparel.offprice.features.welcome.presentation.genderCategory.GenderCategoryScreen
import com.apparel.offprice.features.welcome.presentation.location.ChooseLocationScreen
import com.apparel.offprice.features.welcome.presentation.splash.SplashScreen
import com.apparel.offprice.features.wishlist.presentation.screen.WishListScreen


@Composable
fun AppRoutes(windowSizeClass: WindowSizeClass) {

    val navController = rememberNavController()

    val widthSizeClass = windowSizeClass.widthSizeClass
    val heightSizeClass = windowSizeClass.heightSizeClass

    NavHost(
        navController = navController,
        startDestination = AppScreen.SplashScreen
    ) {
        composable<AppScreen.SplashScreen> {
            SplashScreen(
                onNavigateToHomeScreen = {
                    /*navController.navigate(AppScreen.HomeScreen) {
                        popUpTo(0) { inclusive = true }
                    }*/
                    navController.navigate(AppScreen.OTPScreen)
                },
                onNavigateToRegionSelection = {
                    navController.navigate(AppScreen.LocationSelectionScreen)
                }
            )
        }

        composable<AppScreen.LoginScreen> {

        }

        composable<AppScreen.RegistrationScreen> {

        }

        composable<AppScreen.ForgetPasswordScreen> {

        }

        composable<AppScreen.OTPScreen> {

        }

        composable<AppScreen.ResetPasswordScreen> {

        }

        composable<AppScreen.HomeScreen> {
            HomeScreen(outerNavControl = navController)
        }

        composable<AppScreen.SearchScreen> {
            SearchScreen(
                onSearchSubmit = { productId ->
                    // TODO : Added the PLP navigation from here
                },
                onNavigateToBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<AppScreen.StoreLocatorScreen> {
            StoreLocatorScreen()
        }

        composable<AppScreen.WishListScreen> {
            WishListScreen(
                onNavigateToBack = {
                    navController.popBackStack()
                },
                onStartShoppingClicked = {
                    navController.navigate(AppScreen.HomeScreen){
                        popUpTo(AppScreen.HomeScreen) { inclusive = true }
                    }
                },
                onNavigateToCart = {

                },
                onNavigateToPDP = { product ->
                    navController.navigate(AppScreen.PDPScreen){}
                }
            )
        }

        composable<AppScreen.LocationSelectionScreen> {
            ChooseLocationScreen(onNavigateToNextScreen = {
                navController.navigate(AppScreen.GenderCategoryScreen)
            })
        }

        composable<AppScreen.GenderCategoryScreen> {
            GenderCategoryScreen(
                onCategoryClick = {
                    navController.navigate(AppScreen.HomeScreen) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onSearchClick = {
                    navController.navigate(AppScreen.SearchScreen)
                },
                onWishlistClick = {
                    navController.navigate(AppScreen.WishListScreen)
                }
            )
        }

        composable<AppScreen.PDPScreen> {
            PDPscreen()
        }

        composable<AppScreen.UserProfileScreen> {
            UserProfileScreen(
                onNavigateToBack = {navController.popBackStack()},
                onPersonalItemClicked = {
                    navController.navigate(AppScreen.ProfileDetailScreen)
                },
                onMySizeItemClicked = {
                    navController.navigate(AppScreen.ProfileSizeScreen)
                }
            )
        }

        composable<AppScreen.ProfileDetailScreen> {
            ProfileDetailsScreen(
                onNavigateToBack = {
                    navController.popBackStack()
                },
                onNavigateToPassword = {}
            )
        }

        composable<AppScreen.ProfileSizeScreen> {
            ProfileSizeScreen()
        }

    }

}