package com.example.daily.custom.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.daily.currentUser
import com.example.daily.custom.CaseData
import kotlin.math.floor


@Composable
fun CaseScreen(modifier: Modifier = Modifier, case: CaseData) {
    val isOpenAlertDialog = remember { mutableStateOf(false) }

    LazyColumn(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Box(Modifier.fillMaxWidth()) { Button(onClick = {}) { Text("Назад") } } }
            item { Text(case.name, fontSize = 50.sp) }
            item { Image(bitmap = ImageBitmap.imageResource(case.imageId), contentDescription = null,
                modifier = modifier
                    .size(200.dp)
            ) }
            item { Text("Описание: ${case.description}", modifier.padding(30.dp).padding(30.dp) ) }
            item { Text("В наличии: ${currentUser.inventory2[case]}") }
            item { Text("Может выпасть:") }
            item {
                Row {
                    for (el in case.content) {
                        Column {
                            Image(bitmap = ImageBitmap.imageResource(el.key.iconId), contentDescription = null, modifier = Modifier.size(50.dp))
                            Text(el.key.name)
                            Text("${el.value}%")
                        }
                    }
                }
            }
            item {
                Button(onClick = { isOpenAlertDialog.value = true }, Modifier.size( 200.dp, 75.dp)) {
                    Text("Открыть")
                }
            }
            if (isOpenAlertDialog.value) {
                item {
                    var sliderPosition by remember { mutableFloatStateOf(1f) }
                    AlertDialog(
                        onDismissRequest = { },
                        title = { Text(text = case.name) },
                        text = { Text("Сколько кейсов вы хотите открыть?") },
                        confirmButton = {
                            Column {
                                Slider(
                                    value = sliderPosition,
                                    valueRange = 1f..currentUser.inventory2[case]!!.toFloat(),
                                    steps = currentUser.inventory2[case]!!,
                                    onValueChange = { sliderPosition = it}
                                )
                                Text("${floor(sliderPosition)}")
                                Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                    Button( {isOpenAlertDialog.value = false} ) {
                                    Text("Отмена")
                                }
                                    Button( {} ) {
                                        Text("Открыть")
                                    }
                                }
                            }
                        }
                    )
                }
            }
    }
}
