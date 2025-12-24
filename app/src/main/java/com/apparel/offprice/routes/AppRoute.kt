package com.apparel.offprice.routes

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.features.address.presentation.screen.DeliveryAddressScreen
import com.apparel.offprice.features.authentication.presentation.screen.LoginEmptyScreen
import com.apparel.offprice.features.checkout.presentation.screens.ShippingAddressScreen
import com.apparel.offprice.features.coupon.presentation.screen.CouponScreen
import com.apparel.offprice.features.home.presentation.screens.categories.SubCategoryScreen
import com.apparel.offprice.features.home.presentation.screens.home.HomeScreen
import com.apparel.offprice.features.home.presentation.screens.search.SearchScreen
import com.apparel.offprice.features.paymentCard.presentation.screen.PaymentCardScreen
import com.apparel.offprice.features.pdp.presentation.screen.PDPscreen
import com.apparel.offprice.features.profile.presentation.screen.profileDetails.ProfileDetailsScreen
import com.apparel.offprice.features.profile.presentation.screen.profilePassword.ProfilePasswordScreen
import com.apparel.offprice.features.profile.presentation.screen.profileSize.ProfileSizeScreen
import com.apparel.offprice.features.profile.presentation.screen.userprofile.UserProfileScreen
import com.apparel.offprice.features.storeCredit.presentation.screen.StoreCreditScreen
import com.apparel.offprice.features.storeLocator.presentation.screen.StoreLocatorScreen
import com.apparel.offprice.features.welcome.presentation.genderCategory.GenderCategoryScreen
import com.apparel.offprice.features.welcome.presentation.region.RegionSelectionScreen
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
                    navController.navigate(AppScreen.LocationSelectionScreen) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable<AppScreen.LoginScreen> {
            LoginEmptyScreen(onNavigateBack = {
                navController.popBackStack()
            })
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
            HomeScreen (onNavigateToOuter = { route ->
                navController.navigate(route)
            })
        }

        composable<AppScreen.SearchScreen> {
            SearchScreen(
                onSearchSubmit = { productId ->
                    navController.navigate(AppScreen.PDPScreen)
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
                    navController.navigate(AppScreen.HomeScreen) {
                        popUpTo(AppScreen.HomeScreen) { inclusive = true }
                    }
                },
                onNavigateToCart = {
                    navController.navigate(BottomNavScreen.Item4)
                },
                onNavigateToPDP = { product ->
                    navController.navigate(AppScreen.PDPScreen) {}
                }
            )
        }
        composable<AppScreen.SubCategoryScreen> {
            SubCategoryScreen(
                //parentCategory = it.arguments?.getParcelable("parentCategory")!!,
                onNavigateToSearch = {
                    navController.navigate(AppScreen.SearchScreen)
                },
                onNavigateToWishlist = {
                    navController.navigate(AppScreen.WishListScreen)
                },
                onBack = {
                    navController.popBackStack()
                },
                onNavigateToPLP = { product ->
                    navController.navigate(AppScreen.PDPScreen) {}
                }
            )
        }

        composable<AppScreen.LocationSelectionScreen> {
            RegionSelectionScreen(onNavigateToNextScreen = {
                navController.navigate(AppScreen.GenderCategoryScreen)
            })
        }

        composable<AppScreen.ShippingAddressScreen> {
            ShippingAddressScreen(onBack = {}) { }
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
                onNavigateToBack = { navController.popBackStack() },
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
                onNavigateToPassword = {
                    navController.navigate(AppScreen.ProfilePasswordScreen)
                }
            )
        }

        composable<AppScreen.ProfileSizeScreen> {
            ProfileSizeScreen(
                onNavigateToBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<AppScreen.ProfilePasswordScreen> {
            ProfilePasswordScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<AppScreen.StoreCreditScreen> {
            StoreCreditScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<AppScreen.DeliveryAddressScreen> {
            DeliveryAddressScreen(
                onNavigateToBack = { navController.popBackStack() }
            )
        }

        composable<AppScreen.CouponScreen> {
            CouponScreen(onNavigateBack = {
                navController.popBackStack()
            })
        }

        composable<AppScreen.PaymentCardScreen> {
            PaymentCardScreen(onNavigateBack = {
                navController.popBackStack()
            })
        }

        composable<AppScreen.MyOrdersScreen> {}

        composable<AppScreen.ReturnsScreen> {}

        composable<AppScreen.ExchangeScreen> {}

        composable<AppScreen.LogOutScreen> {}

    }

}