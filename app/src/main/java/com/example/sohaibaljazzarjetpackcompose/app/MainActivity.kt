package com.example.sohaibaljazzarjetpackcompose.app.MainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.sohaibaljazzarjetpackcompose.HomeServiceTheme
import com.example.sohaibaljazzarjetpackcompose.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            HomeServiceTheme {

                // A surface container using the 'background' color from the theme
                Navigation()

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeServiceTheme {
        Greeting("Android")
    }
}
