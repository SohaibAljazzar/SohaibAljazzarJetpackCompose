package com.example.sohaibaljazzarjetpackcompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sohaibaljazzarjetpackcompose.app.core_screens.splash_screen.SplashScreen

@Composable
fun Navigation() {
    val navMainController = rememberNavController()

    NavHost(navController = navMainController, startDestination = "SplashScreen") {
        composable(route = "SplashScreen") {
            SplashScreen(navMainController = navMainController)
        }

        composable(route = "OnBoardingScreen") {
            OnBoardingScreen(navMainController = navMainController)
        }

        composable(route = "OnBoardingScreen2") {
            OnBoardingScreen2(navMainController = navMainController)
        }

        composable(route = "OnBoardingScreen3") {
            OnBoardingScreen3(navMainController = navMainController)
        }



    }
}
