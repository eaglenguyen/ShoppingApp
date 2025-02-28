package com.example.shoppingapp.navigation

import AddressFormScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.shoppingapp.presentation.authscreen.SignInScreen
import com.example.shoppingapp.presentation.authscreen.SignUpScreen
import com.example.shoppingapp.presentation.cart.CartScreen
import com.example.shoppingapp.presentation.checkout.CheckoutScreen
import com.example.shoppingapp.presentation.checkout.OrderCompleteScreen
import com.example.shoppingapp.presentation.home.HomeScreen
import com.example.shoppingapp.presentation.product_info.ProductInfoScreen
import com.example.shoppingapp.presentation.profile.ProfileScreen
import com.example.shoppingapp.presentation.profile.subprofile.settings.SettingsScreen

@Composable
fun NavGraph (
    navController: NavHostController = rememberNavController(),
    ) {


       NavHost(
           navController = navController,
           startDestination = SignUpScreen
       ) {



           composable<HomeScreen> {
               HomeScreen(
                   onClickToSignUp = {
                       navController.navigate(SignUpScreen)
                   },
                   onClickToSettings = {
                       navController.navigate(SettingsScreen)
                   },
                   onClickToDetails = { itemId ->
                       navController.navigate(DetailScreen(itemId))
                   },
                   onClickToCheckOut = {
                       navController.navigate(CheckoutScreen)
                   }

               )
           }

           composable<DetailScreen> { itemId ->
               val positionId = itemId.toRoute<DetailScreen>()
               ProductInfoScreen(
                   id = positionId.id,
                   onClickPrevious = {
                       navController.navigateUp()
                   },
                   onClickToCart = {
                       navController.navigate(CartScreen)
                   }
               )
           }

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



           composable<CartScreen> {

               CartScreen(
                   onBackClick = {
                       navController.navigate(HomeScreen)
                   },
                   onCheckoutClick = {
                       navController.navigate(CheckoutScreen)
                   }
               )
           }


           composable<CheckoutScreen> {
               CheckoutScreen(
                   onClickToCart = {
                       navController.navigate(CartScreen)
                   },
                   onClickToAddressForm = {
                       navController.navigate(AddressFormScreen)
                   },
                   onClickToSuccess = {
                       navController.navigate(SuccessScreen)
                   }

               )
           }

           composable<AddressFormScreen> {

               AddressFormScreen (
                 onBackClick = {
                     navController.navigateUp()
                 }, onSaveClick = {
                     navController.navigate(CheckoutScreen)
                   }
               )
           }

           composable<SuccessScreen> {
               OrderCompleteScreen(
                   onContinueShopping = {
                       navController.navigate(HomeScreen)
                   }
               )
           }

       }

}