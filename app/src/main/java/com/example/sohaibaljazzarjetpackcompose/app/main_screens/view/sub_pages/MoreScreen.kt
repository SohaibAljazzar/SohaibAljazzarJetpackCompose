package com.example.sohaibaljazzarjetpackcompose

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sohaibaljazzarjetpackcompose.app.main_screens.view.HomeScreen

@Composable
fun MoreScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {


        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xff6FC8FB), Color(0xff346EDF)),
                        ),
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    ),
            ) {

                val context = LocalContext.current
                IconButton(
                    onClick = { navigateToHome(context = context) },
                    modifier = Modifier
                        .size(50.dp)
                        .padding(start = 12.dp)
                        .align(alignment = Alignment.CenterStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = "Back",
                    )
                }

                Text(
                    text = "More",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    ),
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 34.dp)
        ) {

            Text(
                text = "Change Password",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 34.dp)
        ) {

            Text(
                text = "FAQ's",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 34.dp)
        ) {

            Text(
                text = "About App",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 34.dp)
        ) {

            Text(
                text = "Terms & Conditions",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 34.dp)
        ) {

            Text(
                text = "Privacy Policy",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 34.dp)
        ) {

            Text(
                text = "Rate App",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 34.dp)
        ) {

            Text(
                text = "Delete Account",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }
    }

}

private fun navigateToHome(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}
