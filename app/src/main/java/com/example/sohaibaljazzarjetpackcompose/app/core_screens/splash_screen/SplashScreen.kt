package com.example.sohaibaljazzarjetpackcompose.app.core_screens.splash_screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sohaibaljazzarjetpackcompose.PreferenceManager
import com.example.sohaibaljazzarjetpackcompose.R
import com.example.sohaibaljazzarjetpackcompose.app.main_screens.view.HomeScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navMainController: NavController) {

    val context = LocalContext.current

    val preferenceManager = PreferenceManager(context)
    val tokenCheck =preferenceManager.getToken().isNullOrBlank()

    LaunchedEffect(key1 = true) {
        delay(3000)

        if (tokenCheck) {
            preferenceManager.saveUserId(-1)
            navMainController.navigate("OnBoardingScreen")

        } else {
            navigateToHomeScreen(context)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF0E9CF9), Color(0xFF6FC8FB))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}

private fun navigateToHomeScreen(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}


