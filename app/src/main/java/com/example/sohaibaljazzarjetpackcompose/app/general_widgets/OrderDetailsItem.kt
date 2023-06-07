package com.example.sohaibaljazzarjetpackcompose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderDetailsItem(model: OrderRequestData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Divider(
                thickness = 2.5.dp,
                color = Color(0xffEAEFFF),
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = "Order #${model.id}",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start
                    ), modifier = Modifier
                        .padding(bottom = 6.dp)
                )
                val inst: OffsetDateTime = OffsetDateTime.ofInstant(
                    Instant.parse(model.createdAt.toString()),
                    ZoneId.systemDefault()
                )
                val res = DateTimeFormatter.ofPattern("dd MMM yyyy").format(inst)
                Text(
                    text = res,
                    style = TextStyle(
                        color = Color.Gray,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                        .align(alignment = Alignment.CenterVertically)
                        .weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = "Service Type : ",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily.SansSerif,

                        ), modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = model.work?.name.toString(),
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,

                        color = Color(0xFF488CE7),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    ), modifier = Modifier
                        .padding(bottom = 26.dp)
                        .align(alignment = Alignment.CenterVertically)
                )
            }
        }
    }
}
