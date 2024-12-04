package com.example.shoppingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppingapp.presentation.authscreen.SignInScreen
import com.example.shoppingapp.presentation.authscreen.SignUpScreen

@Composable
fun NavGraph (
    navController: NavHostController = rememberNavController(),
    ) {

       NavHost(navController = navController, startDestination = SignUpScreen) {
           composable<SignUpScreen> {
               SignUpScreen(
                   onClickToSignIn =  {
                       navController.navigate(SignInScreen)
                   }
               )
           }

           composable<SignInScreen> {
               SignInScreen(
                   onClickPrevious = {
                       navController.navigateUp()
                   }
               )
           }
       }

}