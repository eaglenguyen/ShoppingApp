package com.example.shoppingapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.shoppingapp.presentation.authscreen.SignInScreen
import com.example.shoppingapp.presentation.authscreen.SignUpScreen
import com.example.shoppingapp.presentation.cart.CartScreen
import com.example.shoppingapp.presentation.home.HomeScreen
import com.example.shoppingapp.presentation.product_info.ProductInfoScreen
import com.example.shoppingapp.presentation.product_info.ProductInfoViewModel
import com.example.shoppingapp.presentation.profile.ProfileScreen
import com.example.shoppingapp.presentation.profile.subprofile.settings.SettingsScreen

@Composable
fun NavGraph (
    navController: NavHostController = rememberNavController(),
    ) {


       NavHost(navController = navController, startDestination = SignUpScreen) {
           composable<SignUpScreen> {
               SignUpScreen(
                   onClickToSignIn =  {
                       navController.navigate(SignInScreen)
                   },
                   onClickToHome = {
                       navController.navigate(HomeScreen)
                   }
               )
           }

           composable<SignInScreen> {
               SignInScreen(
                   onClickPrevious = {
                       navController.navigateUp()
                   },
                   onClickToHome = {
                       navController.navigate(HomeScreen)
                   }
               )
           }



           composable<HomeScreen> { backStackEntry ->
               HomeScreen(
                   onClickToSignUp = {
                       navController.navigate(SignUpScreen)
                   },
                   onClickToSettings = {
                       navController.navigate(SettingsScreen)
                   },
                   onClickToDetails = {
                       navController.navigate(DetailScreen(it))
                   }

               )
           }

           composable<ProfileScreen> {
               ProfileScreen(
                   scaffFoldPadding = PaddingValues(),
                   onClickToSignUp = {
                       navController.navigate(SignUpScreen)
                   },
                   onClickToSettings = {
                       navController.navigate(SettingsScreen)
                   }
               )
           }

           composable<SettingsScreen> {
               SettingsScreen(
                   onClickPrevious = {
                       navController.navigateUp()
                   }
               )
           }

           composable<DetailScreen> {
               val positionId = it.toRoute<DetailScreen>()
               ProductInfoScreen(
                   id = positionId.id,
                   onClickPrevious = {
                       navController.navigateUp()
                   },
               )
           }

           composable<CartScreen> {

               CartScreen(
                   onBackClick = { Unit },
                   onCheckoutClick = { Unit }
               )
           }


       }

}