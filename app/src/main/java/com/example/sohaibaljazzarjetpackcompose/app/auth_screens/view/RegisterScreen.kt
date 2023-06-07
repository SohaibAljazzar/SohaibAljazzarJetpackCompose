package com.example.sohaibaljazzarjetpackcompose

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
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import com.example.sohaibaljazzarjetpackcompose.app.auth_screens.view.RegisterScreen.RegisterViewModel
import com.example.sohaibaljazzarjetpackcompose.app.main_screens.view.HomeScreen
import kotlinx.coroutines.delay


class RegisterScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Scaffold() {
                val registerViewModel: RegisterViewModel = viewModel()
                val isRegistered by registerViewModel.isRegistered.collectAsState()
                val error by registerViewModel.error.collectAsState()
                val token by registerViewModel.theToken.collectAsState()
                val userId by registerViewModel.userId.collectAsState()
                val userPhone by registerViewModel.userPhone.collectAsState()


                val context = LocalContext.current

                val preferenceManager = PreferenceManager(context)


                if (isRegistered) {
                    preferenceManager.saveToken(token = token)
                    preferenceManager.saveUserId(userId = userId)
                    preferenceManager.saveUserPhone(userPhone = userPhone)
                    preferenceManager.saveToken(token)
                    navigateTHomeScreen(context)
                }

                error?.let { errorMessage ->
                    // Display the error message
                    Toast.makeText(
                        context, errorMessage, Toast.LENGTH_LONG
                    ).show()

                }

                RegisterScreenView(registerViewModel)
            }
        }


    }

}

@Composable
fun RegisterScreenView(viewModel: RegisterViewModel) {
    val selectedTab = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF0E9CF9), Color(0xFF6FC8FB))
                )
            )
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        BackToLoginBtn()
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(Color.White)
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

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
                    Tab(selected = selectedTab.value == 0, onClick = { selectedTab.value = 0 }) {
                        Text(
                            "Service provider",
                            color = if (selectedTab.value == 0) Color(0xff0E4DFB) else Color.Black
                        )
                    }
                    Tab(selected = selectedTab.value == 1, onClick = { selectedTab.value = 1 }) {
                        Text(
                            "Customer",
                            color = if (selectedTab.value == 1) Color(0xff0E4DFB) else Color.Black
                        )
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
                            ServiceRegisterView()

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
                            CustomerRegisterView(viewModel)
                        }


                    }
                }
            }
        }
    }
}

@Composable
fun CustomerRegisterView(viewModel: RegisterViewModel) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val fullNameState = remember { mutableStateOf(TextFieldValue()) }
    val phoneNumberState = remember { mutableStateOf(TextFieldValue()) }
    val rememberMeState = remember { mutableStateOf(false) }
    Column(
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextField(
            value = fullNameState.value,
            onValueChange = { fullNameState.value = it },

            label = {
                Text(
                    "Full Name",
                    Modifier.padding(2.dp),
                    Color.Gray,
                    fontFamily = FontFamily.SansSerif,

                    )
            },
            modifier = Modifier
                .fillMaxWidth()
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

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(
                fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
            )

        )
        Spacer(modifier = Modifier.height(15.dp))
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
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = phoneNumberState.value,
            onValueChange = { phoneNumberState.value = it },

            label = {
                Text(
                    "Phone Nu.",
                    Modifier.padding(2.dp),
                    Color.Gray,
                    fontFamily = FontFamily.SansSerif,

                    )
            },
            modifier = Modifier
                .fillMaxWidth()
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

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            textStyle = TextStyle(
                fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
            )

        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(colors = TextFieldDefaults.textFieldColors(
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
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Filled.Lock else Icons.Outlined.Lock,
                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                    )
                }
            })
        Spacer(modifier = Modifier.height(15.dp))
        DropdownMenu()
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(23.dp)
                        .background(
                            color = if (rememberMeState.value) Color(0xff0E4DFB) else Color.LightGray,
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
                    text = "I Read and Accept",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Text(text = " Home Service Terms & Conditions",
                color = Color(0xFF0E4DFB),
                fontSize = 12.sp,
                modifier = Modifier.clickable { /* Handle forget password click */ })
        }

        // have account
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Have Account?",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif
                )
                val context = LocalContext.current
                TextButton(modifier = Modifier.padding(bottom = 15.dp),

                    onClick = { /* Handle sign in button click */ }) {
                    Text(modifier = Modifier
                        .padding(bottom = 10.dp)
                        .clickable { navigateToLoginScreen(context) },
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = Color(0xFF0E4DFB)
                                )
                            ) {
                                append("SIGN IN")
                            }
                        })
                }
            }

            TextButton(modifier = Modifier
                .size(width = 164.dp, height = 55.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF346EDF), Color(0xFF6FC8FB))
                    )
                ), onClick = {
                val name = fullNameState.value.text
                val email = emailState.value.text
                val password = passwordState.value.text
                val phone = phoneNumberState.value.text

                viewModel.register(name, email, password, phone)
            }) {
                Text(text = "SIGN UP", color = Color.White)
            }
        }
    }
}

@Composable
fun ServiceRegisterView() {
    var passwordVisibility by remember { mutableStateOf(false) }
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val fullNameState = remember { mutableStateOf(TextFieldValue()) }
    val phoneNumberState = remember { mutableStateOf(TextFieldValue()) }
    val rememberMeState = remember { mutableStateOf(false) }
    Column(
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextField(
            value = fullNameState.value,
            onValueChange = { fullNameState.value = it },

            label = {
                Text(
                    "Full Name",
                    Modifier.padding(2.dp),
                    Color.Gray,
                    fontFamily = FontFamily.SansSerif,

                    )
            },
            modifier = Modifier
                .fillMaxWidth()
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

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(
                fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
            )

        )
        Spacer(modifier = Modifier.height(15.dp))
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
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = phoneNumberState.value,
            onValueChange = { phoneNumberState.value = it },

            label = {
                Text(
                    "Phone Nu.",
                    Modifier.padding(2.dp),
                    Color.Gray,
                    fontFamily = FontFamily.SansSerif,

                    )
            },
            modifier = Modifier
                .fillMaxWidth()
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

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            textStyle = TextStyle(
                fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
            )

        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(colors = TextFieldDefaults.textFieldColors(
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
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Filled.Lock else Icons.Outlined.Lock,
                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                    )
                }
            })
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(23.dp)
                        .background(
                            color = if (rememberMeState.value) Color(0xff0E4DFB) else Color.LightGray,
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
                    text = "I Read and Accept",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Text(text = " Home Service Terms & Conditions",
                color = Color(0xFF0E4DFB),
                fontSize = 12.sp,
                modifier = Modifier.clickable { /* Handle forget password click */ })
        }

        // have account
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Have Account?",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif
                )
                val context = LocalContext.current
                TextButton(modifier = Modifier.padding(bottom = 15.dp),

                    onClick = { /* Handle sign in button click */ }) {
                    Text(modifier = Modifier
                        .padding(bottom = 10.dp)
                        .clickable { navigateToLoginScreen(context) },
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = Color(0xFF0E4DFB)
                                )
                            ) {
                                append("SIGN IN")
                            }
                        })
                }
            }

            TextButton(modifier = Modifier
                .size(width = 164.dp, height = 55.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF346EDF), Color(0xFF6FC8FB))
                    )
                ), onClick = {}) {
                Text(text = "SIGN UP", color = Color.White)
            }
        }
    }
}

private fun navigateToLoginScreen(context: Context) {
    val intent = Intent(context, LoginScreen::class.java).apply {}
    context.startActivity(intent)
}

private fun navigateTHomeScreen(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}

@Composable
fun BackToLoginBtn() {
    val context = LocalContext.current
    IconButton(
        onClick = { navigateToLoginScreen(context) },
        modifier = Modifier
            .size(50.dp)
            .padding(start = 12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.back_icon),
            contentDescription = "Back",
        )
    }
}

@Composable
fun DropdownMenu() {
    val expanded = remember { mutableStateOf(false) }

    Column {
        Button(
            onClick = { expanded.value = !expanded.value },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .background(Color(0xFFF3F3F3))
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)), shape = RoundedCornerShape(8.dp)
                )
                .background(Color(0xFFF3F3F3)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFF3F3F3), // Set the background color to gray
                contentColor = Color.Black // Set the content (text) color to black
            )
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Select Service", color = Color(0xFFC2CECE))
                Spacer(modifier = Modifier.width(200.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFFC2CECE)
                )
            }
        }
    }
}