package com.example.daily.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

data class CaseData(
    var imageId : Int,
    var name : String,
    var description : String,
    var price : Float,
    var content : MutableMap<ItemData, Float>
)

@Composable
fun CaseData.Print(modifier: Modifier = Modifier) {
    Column(modifier = modifier.clickable {  }, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(imageId), name)
        Text(name)
    }
}