package com.example.sohaibaljazzarjetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ItemWorkServiceCard(model: AllWorkData, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(95.dp)
            .width(95.dp)
            .border(0.5.dp, color = Color(0xFFDE1487), shape = RoundedCornerShape(10.dp))
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(10.dp))
            .clickable { onItemClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.shape1),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .padding(5.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = model.name.toString(),
                style = TextStyle(fontSize = 11.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color(0xff0E4DFB)
            )
        }
    }
}



