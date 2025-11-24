package com.apparel.offprice.routes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.features.home.presentation.screens.home.HomeScreen
import com.apparel.offprice.features.home.presentation.screens.search.SearchScreen
import com.apparel.offprice.features.storeLocator.presentation.screen.StoreLocatorScreen
import com.apparel.offprice.features.welcome.presentation.genderCategory.GenderCategoryScreen
import com.apparel.offprice.features.welcome.presentation.location.ChooseLocationScreen
import com.apparel.offprice.features.wishlist.presentation.screen.WishListScreen


@Composable
fun AppRoutes(windowSizeClass: WindowSizeClass) {

    val navController = rememberNavController()

    val widthSizeClass = windowSizeClass.widthSizeClass
    val heightSizeClass = windowSizeClass.heightSizeClass
    val vm: AppRouteViewModel = hiltViewModel()
    val location = vm.userLocation.collectAsState().value
    val gender = vm.userGender.collectAsState().value
    val isDataLoaded by vm.isDataLoaded.collectAsState()


    if (!isDataLoaded) {
        // Show a splash screen or loader
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }


    val startDestination =
        when {
            location == null -> AppScreen.LocationSelectionScreen
            gender == null -> AppScreen.GenderCategoryScreen
            else -> AppScreen.HomeScreen
        }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable<AppScreen.SplashScreen> {
            ChooseLocationScreen(onItemClick = {
                navController.navigate(AppScreen.LocationSelectionScreen)
            })
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
            SearchScreen()
        }

        composable<AppScreen.StoreLocatorScreen> {
            StoreLocatorScreen()
        }

        composable<AppScreen.WishListScreen> {
            WishListScreen()
        }

        composable<AppScreen.LocationSelectionScreen> {
            ChooseLocationScreen(
                onItemClick = { locationItem ->
                    vm.saveLocation(locationItem.id)
                    navController.navigate(AppScreen.GenderCategoryScreen)
                }
            )
        }

        composable<AppScreen.GenderCategoryScreen> {
            GenderCategoryScreen(
                onCategoryClick = { genderItem ->
                    vm.saveGender(genderItem.id)

                    // Go to HOME and CLEAR stack
                    navController.navigate(AppScreen.HomeScreen) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

    }

}