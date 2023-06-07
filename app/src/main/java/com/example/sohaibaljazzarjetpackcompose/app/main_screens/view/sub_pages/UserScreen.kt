package com.example.sohaibaljazzarjetpackcompose

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserScreen() {

    Box(
        modifier = Modifier.background(
            brush = Brush.horizontalGradient(
                colors = listOf(Color(0xff6FC8FB), Color(0xff346EDF)),
            )
        )
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        IconButton(
            onClick = {},
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(end = 20.dp, top = 20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_setting),
                contentDescription = "Settings",
                modifier = Modifier.size(30.dp),
                tint = Color.White // Set the tint color to white
            )
        }

        val overlayBoxHeight = 85.dp
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 145.dp), shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
        ) {


            Column(modifier = Modifier.padding(top = 75.dp)) {


                Text(
                    text = "Sohaib Aljazzar",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        color = Color(0xff293340)
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(bottom = 4.dp, top = 4.dp)
                )

                Text(
                    text = "Gaza, Palestine",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        color = Color(0xff272727)
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(bottom = 26.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xffF6F7FB)
                        )
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 64.dp)
                ) {

                    Text(
                        text = "Language",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .weight(1f)
                    )
                    Text(
                        text = "English",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.End,
                            color = Color(0xffC2CECE)
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .weight(1f)
                            .padding(end = 8.dp)
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
                        text = "My Rates",
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
                        text = "Contact us",
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
                        .padding(start = 20.dp, end = 20.dp, top = 34.dp, bottom = 30.dp)
                ) {

                    Text(
                        text = "Share App",
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

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xffF6F7FB)
                        )
                )


                val context = LocalContext.current
                val preferenceManager = PreferenceManager(context)
                TextButton(
                    onClick = {
                        preferenceManager.deleteToken()
                        preferenceManager.deleteUserId()
                        preferenceManager.deleteUserUserPhone()
                        navigateToLoginScreen(context)
                    },
                    Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "icon",
                        tint = Color(0xffAF8344)
                    )
                    Text(
                        text = "SIGN OUT", fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color(0xff0E4DFB), modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xffF6F7FB)
                        )
                )

            }
        }
        Image(
            painterResource(id = R.drawable.sohaib),
            contentDescription = "",
            modifier = Modifier
                .align(TopCenter)
                .offset(x = 0.dp, y = overlayBoxHeight)
                .width(120.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    BorderStroke(4.dp, Color.White),
                    RoundedCornerShape(16.dp)
                )
                .padding(4.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds,
        )
    }


}

private fun navigateToLoginScreen(context: Context) {
    val intent = Intent(context, LoginScreen::class.java).apply {}
    context.startActivity(intent)
}






