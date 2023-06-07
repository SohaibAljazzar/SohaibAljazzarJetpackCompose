package com.example.sohaibaljazzarjetpackcompose

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ServiceScreen() {

    val viewModel: AllWorkViewModel = viewModel()
    val listModel = viewModel.listAllWorkLiveData
    val context = LocalContext.current

    viewModel.getAllWork()

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(250.dp)
//                .background(
//                    brush = Brush.horizontalGradient(
//                        colors = listOf(Color(0xff346EDF), Color(0xff6FC8FB)),
//                    ),
//                    shape = RoundedCornerShape(bottomStartPercent = 30, bottomEndPercent = 30)
//                ),
//
//            ) {
//
//            Column(
//                modifier = Modifier.fillMaxWidth(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//
//                Image(
//                    painter = painterResource(id = R.drawable.app_icon),
//                    contentDescription = "app_icon",
//                    modifier = Modifier
//                        .width(80.dp)
//                        .height(80.dp)
//                )
//
//
//                Text(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(top = 18.dp, bottom = 18.dp),
//                    text = "Services",
//                    fontFamily = FontFamily.SansSerif,
//                    color = Color.White,
//                    textAlign = TextAlign.Center,
//                    fontSize = 18.sp
//
//                )
//
//            }
//
//        }
//
//
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3),
//            contentPadding = PaddingValues(16.dp),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 20.dp)
//        ) {
//            itemsIndexed(items = listModel) { index, item ->
//                ItemWorkServiceCard(model = item, onItemClick = {
//
//                    val preferenceManager = PreferenceManager(context)
//                    val userId = preferenceManager.getUserID()
//
//                    if (userId != -1) {
//                        item.id?.let {
//                            navigateToCreateOrderClass(
//                                context = context,
//                                it,
//                                itemName = item.name.toString()
//                            )
//                        }
//                    } else {
//                        Toast.makeText(context, "Please Login to Create order", Toast.LENGTH_LONG)
//                            .show()
//                        navigateToLoginScreen(context)
//                    }
//
//                })
//
//            }
//        }
//
//    }

    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(254.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp),
            backgroundColor = Color(0xff2196F3),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .align(Alignment.CenterHorizontally) // Align the column content horizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_app),
                            contentDescription = "image app",
                            modifier = Modifier
                                .width(53.09.dp)
                                .height(33.35.dp)
                                .align(alignment = Alignment.Center)
                        )
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .align(alignment = Alignment.TopEnd)
                                .padding(end = 19.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.shape),
                                contentDescription = "Notifications",
                                modifier = Modifier.size(30.dp),
                                tint = Color.White // Set the tint color to white
                            )
                        }
                    }
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Card(
                                    modifier = Modifier
                                        .width(300.dp)
                                        .height(87.dp),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                }
                                Spacer(modifier = Modifier.padding(4.dp))
                            }
                        }
                    }


                }
            }
        }

        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .weight(4f)
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Select Service",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xff0E4DFB)
                    )
                )
            }

            LazyVerticalGrid(
                GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 2.dp, vertical = 30.dp)

            ) {
                itemsIndexed(items = listModel) { index, item ->
                    ItemWorkServiceCard(model = item, onItemClick = {

                        val preferenceManager = PreferenceManager(context)
                        val userId = preferenceManager.getUserID()

                        if (userId != -1) {
                            item.id?.let {
                                navigateToCreateOrderClass(
                                    context = context,
                                    it,
                                    itemName = item.name.toString()
                                )
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Please Login to Create order",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            navigateToLoginScreen(context)
                        }

                    })

                }
            }

        }
    }


}

private fun navigateToCreateOrderClass(context: Context, itemId: Int, itemName: String) {
    val intent = Intent(context, CreateOrderScreen::class.java).apply {
        putExtra("itemId", itemId)
        putExtra("itemName", itemName)
    }
    context.startActivity(intent)
}

private fun navigateToLoginScreen(context: Context) {
    val intent = Intent(context, LoginScreen::class.java).apply {

    }
    context.startActivity(intent)
}


