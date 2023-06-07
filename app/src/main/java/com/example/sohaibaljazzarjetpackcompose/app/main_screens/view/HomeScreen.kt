package com.example.sohaibaljazzarjetpackcompose.app.main_screens.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sohaibaljazzarjetpackcompose.BottomBarItems
import com.example.sohaibaljazzarjetpackcompose.app.main_screens.BottomNavGraph


class HomeScreen : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { BottomBar(navController = navController) },

                ) {
                BottomNavGraph(navController = navController)

            }
        }
    }

}


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarItems.Service,
        BottomBarItems.Orders,
        BottomBarItems.User,
        BottomBarItems.More,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination




    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xff6FC8FB)
            )
    ) {

        BottomNavigation(
            modifier = Modifier
                .height(84.dp)
                .padding(bottom = 15.dp)
                .background(Color(0xff6FC8FB)),
            backgroundColor = Color(0xff6FC8FB),
            elevation = 0.dp,

            ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarItems,
    currentDestination: NavDestination?,
    navController: NavHostController,

    ) {

    val selectedIconColor = Color.White // Set the selected icon color here
    val unselectedIconColor = Color(0xBEFFFFFF) // Set the unselected icon color here

    BottomNavigationItem(
        label = {
            val titleColor =
                if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                    selectedIconColor
                } else {
                    unselectedIconColor
                }
            Text(
                text = screen.title,
                color = titleColor,
                fontSize = 11.sp,
                fontFamily = FontFamily.SansSerif,
            )
        },

        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,


        icon = {
            val iconColor =
                if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                    selectedIconColor
                } else {
                    unselectedIconColor
                }
            Image(

                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon",
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp),
                colorFilter = ColorFilter.tint(iconColor)


            )
        },
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },


        )
}