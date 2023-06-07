package com.example.sohaibaljazzarjetpackcompose

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sohaibaljazzarjetpackcompose.app.main_screens.view.HomeScreen
import com.example.sohaibaljazzarjetpackcompose.app.main_screens.viewModel.CreateOrderViewModel
import com.google.android.gms.maps.model.LatLng


class CreateOrderScreen : ComponentActivity(), LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)
    private val createOrderViewModel: CreateOrderViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)

        setContent {


            val itemId = intent.getIntExtra("itemId", -1)
            val itemName = intent.getStringExtra("itemName")
            val navController = rememberNavController()
            val context = LocalContext.current

            val preferenceManager = PreferenceManager(context)
            val userId = preferenceManager.getUserID()





            HomeServiceTheme {

                NavHost(navController, startDestination = "createOrderProblemDetailsScreen") {


                    composable("createOrderDone") {
                        CreateOrderDone(navController)
                    }

                    composable("createOrderProblemDetailsScreen") {


                        if (itemName != null) {
                            val userPhone = preferenceManager.getUserPhone()

                            createOrderViewModel.newOrderRequestResponse.observe(this@CreateOrderScreen) { response ->

                                if (response.success == true) {
                                    navController.navigate("createOrderDone")
                                } else {
                                    Toast.makeText(
                                        this@CreateOrderScreen,
                                        "Error : ${response.message}",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }
                            }

                            createOrderViewModel.error.observe(this@CreateOrderScreen) { errorMessage ->
                                Toast.makeText(
                                    this@CreateOrderScreen,
                                    errorMessage,
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                            CreateOrderProblemDetailsScreen(
                                navController = navController,
                                itemId = itemId,
                                itemName = itemName,
                                userId = userId,
                                userPhone = userPhone.toString(), viewModel = createOrderViewModel
                            )
                        } else {
                            Toast.makeText(context, "No Service Name", Toast.LENGTH_LONG).show()
                        }

                    }


                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

}

@Composable
fun CreateOrderProblemDetailsScreen(
    navController: NavHostController,
    itemId: Int,
    userId: Int,
    userPhone: String,
    itemName: String,
    viewModel: CreateOrderViewModel
) {


    val problemState = remember { mutableStateOf(TextFieldValue()) }

    val singapore = LatLng(1.35, 103.87)






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
                    text = itemName,
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



        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                .border(
                    BorderStroke(1.dp, Color(0xFF868686)), shape = RoundedCornerShape(6.dp)
                ),
        ) {

            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {

                Image(
                    painter = painterResource(id = R.drawable.image_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                        .align(alignment = Alignment.CenterStart)
                )

                Text(
                    text = "Image Problem", style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    ), modifier = Modifier.align(alignment = Alignment.Center)

                )
            }

        }

        Text(
            text = "image must be no more than 2 MB Max 5 Image", style = TextStyle(
                fontSize = 8.sp,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Start,
                color = Color.Gray
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp)
        )


        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            TextField(
                value = problemState.value,
                onValueChange = { problemState.value = it },
                label = {
                    Text(
                        "More Details About Problem …",
                        Modifier.padding(2.dp),
                        Color.Gray,
                        fontFamily = FontFamily.SansSerif,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color.White)
                    .border(
                        border = BorderStroke(1.dp, Color(0xff346EDF)),
                        shape = RoundedCornerShape(8.dp),
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                maxLines = 10,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start
                )
            )
        }
        Spacer(modifier = Modifier.height(280.dp))
        val context = LocalContext.current

        CustomButton(
            onClick = {

                val problemDes = problemState.value.text
                val preferenceManager = PreferenceManager(context)
                val userToken = preferenceManager.getToken()

                viewModel.createOrder(
                    userToken.toString(),
                    userId,
                    itemId,
                    problemDes,
                    singapore.latitude.toString(),
                    singapore.longitude.toString(),
                    userPhone,
                )


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 35.dp, start = 35.dp, end = 35.dp)
                .height(55.dp)
                .background(color = Color.Black)
                .border(
                    BorderStroke(1.dp, Color.Transparent),
                    shape = RoundedCornerShape(6.dp)
                ),
            gradientColors = listOf(
                Color.Black, Color.Black
            )
        ) {
            Text(
                text = "ADD ORDER",
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }


    }


}

@Composable
fun CreateOrderDone(navController: NavController) {
    val btnStartColor = Color(0xFF346EDF) // Green color
    val btnEndColor = Color(0xFF6FC8FB)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.illustration),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(71.dp))
        Row {
            Text(text = "ORDER ", color = Color.Black, fontSize = 24.sp)
            Text(text = "DONE", color = Color(0xff0E4DFB), fontSize = 24.sp)
            Text(text = "!", color = Color(0xffAF8344), fontSize = 24.sp)
        }
        Spacer(Modifier.height(15.dp))
        Text(
            modifier = Modifier.padding(horizontal = 45.dp),
            text = "The ORDER has been sent. A technician will contact you",
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(20.dp))
        val context = LocalContext.current
        TextButton(
            modifier = Modifier
                .size(width = 255.dp, height = 55.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(btnStartColor, btnEndColor)
                    )
                ),
            onClick = { navigateToHome(context) }
        ) {
            Text(text = "GO TO HOME", color = Color.White, fontSize = 18.sp)
        }
    }
}

private fun navigateToHome(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}





