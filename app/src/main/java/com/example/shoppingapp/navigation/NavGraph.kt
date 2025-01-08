package com.example.shoppingapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppingapp.presentation.authscreen.SignInScreen
import com.example.shoppingapp.presentation.authscreen.SignUpScreen
import com.example.shoppingapp.presentation.home.HomeScreen
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



           composable<HomeScreen> {
               HomeScreen(
                   onClickToSignUp = {
                       navController.navigate(SignUpScreen)
                   },
                   onClickToSettings = {
                       navController.navigate(SettingsScreen)
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

       }

}