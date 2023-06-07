package com.example.sohaibaljazzarjetpackcompose

import com.example.sohaibaljazzarjetpackcompose.*
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sohaibaljazzarjetpackcompose.app.auth_screens.view_model.LoginViewModel
import com.example.sohaibaljazzarjetpackcompose.app.main_screens.view.HomeScreen
import kotlinx.coroutines.delay


class LoginScreen : ComponentActivity() {


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Scaffold() {
                val tabTitles = listOf("Service Provider", "Customer")
                val selectedTab = remember { mutableStateOf(0) }
                val loginViewModel: LoginViewModel = viewModel()

                val context = LocalContext.current
                val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()
                val error by loginViewModel.error.collectAsState()
                val token by loginViewModel.theToken.collectAsState()
                val userId by loginViewModel.userId.collectAsState()
                val userPhone by loginViewModel.userPhone.collectAsState()


                val preferenceManager = PreferenceManager(context)




                if (isLoggedIn) {
                    preferenceManager.saveToken(token = token)
                    preferenceManager.saveUserId(userId = userId)
                    preferenceManager.saveUserPhone(userPhone = userPhone)

                    navigateToHomeScreen(context)
                }

                error?.let { errorMessage ->
                    // Display the error message
                    Toast.makeText(
                        context,
                        errorMessage,
                        Toast.LENGTH_LONG
                    ).show()

                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF0E9CF9), Color(0xFF6FC8FB))
                            )
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 80.dp)
                            .fillMaxWidth()
                    ) {
                        // Add your image here
                        Image(
                            painter = painterResource(R.drawable.image_app),
                            contentDescription = "Image",
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                            .background(Color.White)
                    ) {
                        Column(modifier = Modifier.padding(15.dp)) {
                            TabRow(
                                selectedTabIndex = selectedTab.value,
                                backgroundColor = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                indicator = { tabPositions ->
                                    Box(
                                        modifier = Modifier
                                            .tabIndicatorOffset(tabPositions[selectedTab.value])
                                            .height(2.dp)
                                            .padding(horizontal = 80.dp)
                                            .background(color = Color(0xff0E4DFB))
                                    )
                                },
                            ) {
                                tabTitles.forEachIndexed { index, title ->
                                    Tab(selected = selectedTab.value == index,
                                        selectedContentColor = Color(0xff0E4DFB),
                                        unselectedContentColor = Color.Black,
                                        onClick = { selectedTab.value = index },
                                        text = {
                                            Text(
                                                text = title,
                                                fontFamily = FontFamily.SansSerif,
                                            )
                                        })
                                }
                            }

                            when (selectedTab.value) {
                                0 -> {


                                    var isVisible by remember { mutableStateOf(false) }

                                    LaunchedEffect(Unit) {
                                        // Start the timer when the composable is first composed
                                        isVisible = false

                                        // Delay for 2 seconds
                                        delay(0)

                                        // After the delay, hide the view
                                        isVisible = true
                                    }

                                    AnimatedVisibility(
                                        visible = isVisible,
                                        enter = slideInHorizontally(
                                            initialOffsetX = { 100 }, // Initial offset to the left
                                            animationSpec = TweenSpec(300) // Animation duration
                                        ),
                                    ) {
                                        ServiceProviderView()
                                    }


                                }

                                1 -> {

                                    var isVisible by remember { mutableStateOf(false) }

                                    LaunchedEffect(Unit) {
                                        // Start the timer when the composable is first composed
                                        isVisible = false

                                        // Delay for 2 seconds
                                        delay(0)

                                        // After the delay, hide the view
                                        isVisible = true
                                    }

                                    AnimatedVisibility(
                                        visible = isVisible,
                                        enter = slideInHorizontally(
                                            initialOffsetX = { -100 }, // Initial offset to the left
                                            animationSpec = TweenSpec(300) // Animation duration
                                        ),
                                    ) {
                                        LoginScreenView(loginViewModel = loginViewModel)

                                    }


                                }
                            }

                        }
                    }
                }


            }

        }


    }

    override fun onBackPressed() {

    }
}


@Composable
fun LoginScreenView(loginViewModel: LoginViewModel) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val rememberMeState = remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },

            label = {
                Text(
                    "Email",
                    Modifier.padding(2.dp),
                    Color.Gray,
                    fontFamily = FontFamily.SansSerif,

                    )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(Color(0xFFF3F3F3))
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)), shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(
                fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
            )

        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF3F3F3))
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)), shape = RoundedCornerShape(8.dp)
                ),
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = {
                Text(
                    "Password",
                    Modifier.padding(2.dp),
                    Color.Gray,
                    fontFamily = FontFamily.SansSerif,
                )
            },

            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Filled.Lock else Icons.Outlined.Lock,
                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(25.dp))
        /// Remember me
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(23.dp)
                        .background(
                            color = if (rememberMeState.value) Color(0xff0E4DFB) else Color(
                                0xFFF3F3F3
                            ),
                            shape = CircleShape,
                        )
                        .clickable { rememberMeState.value = !rememberMeState.value },
                ) {
                    Checkbox(
                        checked = rememberMeState.value,
                        onCheckedChange = { rememberMeState.value = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Transparent,
                            uncheckedColor = Color.Transparent,
                            checkmarkColor = Color.White
                        ),
                    )
                }
                Text(
                    text = "Remember me", modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            Text(
                text = "Forget password?",
                modifier = Modifier.clickable { /* Handle forget password click */ }
            )
        }

        // have account
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "New Member?",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif
                )

                val context = LocalContext.current
                TextButton(modifier = Modifier.padding(bottom = 15.dp),
                    onClick = { navigateToRegisterScreen(context = context) }
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = Color(0xff0E4DFB), fontSize = 16.sp
                                )
                            ) {
                                append("SIGN UP")
                            }
                        }
                    )
                }
            }

            TextButton(
                modifier = Modifier
                    .size(width = 164.dp, height = 55.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF0E9CF9), Color(0xFF6FC8FB))
                        )
                    ),
                onClick = {
                    val email = emailState.value.text
                    val password = passwordState.value.text
                    loginViewModel.login(
                        email = email,
                        password = password
                    )
                }
            ) {
                Text(text = "LOGIN", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(120.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 105.dp)
        ) {
            val context = LocalContext.current
            ClickableText(
                text = AnnotatedString("Get Start Now"),
                onClick = { navigateToHomeScreen(context = context) },
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Right Arrow",
                tint = Color.Black
            )
        }
    }


}


@Composable
fun ServiceProviderView() {
    var passwordVisibility by remember { mutableStateOf(false) }
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val rememberMeState = remember { mutableStateOf(false) }


    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },

            label = {
                Text(
                    "Email",
                    Modifier.padding(2.dp),
                    Color.Gray,
                    fontFamily = FontFamily.SansSerif,

                    )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(Color(0xFFF3F3F3))
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)), shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(
                fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
            )

        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF3F3F3))
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)), shape = RoundedCornerShape(8.dp)
                ),
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },

            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Filled.Lock else Icons.Outlined.Lock,
                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(25.dp))
        /// Remember me
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(23.dp)
                        .background(
                            color = if (rememberMeState.value) Color(0xff0E4DFB) else Color(
                                0xFFF3F3F3
                            ),
                            shape = CircleShape,
                        )
                        .clickable { rememberMeState.value = !rememberMeState.value },
                ) {
                    Checkbox(
                        checked = rememberMeState.value,
                        onCheckedChange = { rememberMeState.value = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Transparent,
                            uncheckedColor = Color.Transparent,
                            checkmarkColor = Color.White
                        ),
                    )
                }
                Text(
                    text = "Remember me", modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            Text(
                text = "Forget password?",
                modifier = Modifier.clickable { /* Handle forget password click */ }
            )
        }

        // have account
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "New Member?",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif
                )

                val context = LocalContext.current
                TextButton(modifier = Modifier.padding(bottom = 15.dp),
                    onClick = { navigateToRegisterScreen(context = context) }
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = Color(0xff0E4DFB), fontSize = 16.sp
                                )
                            ) {
                                append("SIGN UP")
                            }
                        }
                    )
                }
            }

            TextButton(
                modifier = Modifier
                    .size(width = 164.dp, height = 55.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF0E9CF9), Color(0xFF6FC8FB))
                        )
                    ),
                onClick = {/* Perform login action */ }
            ) {
                Text(text = "LOGIN", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(120.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 105.dp)
        ) {
            val context = LocalContext.current
            ClickableText(
                text = AnnotatedString("Get Start Now"),
                onClick = { navigateToHomeScreen(context = context) },
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Right Arrow",
                tint = Color.Black
            )
        }
    }

}

private fun navigateToHomeScreen(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}

private fun navigateToRegisterScreen(context: Context) {
    val intent = Intent(context, RegisterScreen::class.java).apply {}
    context.startActivity(intent)
}


