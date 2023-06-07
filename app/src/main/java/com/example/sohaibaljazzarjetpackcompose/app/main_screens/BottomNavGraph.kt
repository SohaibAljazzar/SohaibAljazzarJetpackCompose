package com.example.sohaibaljazzarjetpackcompose.app.main_screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sohaibaljazzarjetpackcompose.BottomBarItems
import com.example.sohaibaljazzarjetpackcompose.MoreScreen
import com.example.sohaibaljazzarjetpackcompose.OrdersScreen
import com.example.sohaibaljazzarjetpackcompose.ServiceScreen
import com.example.sohaibaljazzarjetpackcompose.UserScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarItems.Service.route
    ) {
        composable(route = BottomBarItems.Service.route) {
            ServiceScreen()

        }
        composable(route = BottomBarItems.Orders.route) {
            OrdersScreen()
        }
        composable(route = BottomBarItems.User.route) {
            UserScreen()
        }
        composable(route = BottomBarItems.More.route) {
            MoreScreen()
        }


    }
}
