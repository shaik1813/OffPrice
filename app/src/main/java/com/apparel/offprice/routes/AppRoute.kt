package com.apparel.offprice.routes

import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.features.home.presentation.screens.HomeScreen


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
            HomeScreen()
        }

    }

}