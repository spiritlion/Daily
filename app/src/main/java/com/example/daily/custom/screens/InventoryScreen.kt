package com.example.daily.custom.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.daily.currentUser
import com.example.daily.custom.ItemData
import com.example.daily.custom.Print
import com.example.daily.ui.theme.Blue
import com.example.daily.ui.theme.Green
import com.example.daily.ui.theme.LightYellow
import com.example.daily.ui.theme.Purple
import com.example.daily.ui.theme.Red
import com.example.daily.ui.theme.Yellow

var scale = mutableIntStateOf(1)
var isItemDescription = mutableStateOf<ItemData?>(null)

@Composable
fun InventoryScreen() {
    Column (modifier = Modifier.fillMaxSize()) {
        Row {
            Box {
                Image(
                    painter = ColorPainter(Color.Black),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp, 400.dp)
                )
                Text("Здесь будет аватар.")
            }

            Column {
                Row {
                    if (currentUser.avatar.hat == null) {
                        Text("Шапка: - ")
                    } else {
                        Text("Шапка: ")
                        Column(modifier = Modifier.clickable { isItemDescription.value = currentUser.avatar.hat  }) {
                            Image(
                                painter = painterResource(currentUser.avatar.hat!!.iconId),
                                contentDescription = null
                            )
                            Text(currentUser.avatar.hat!!.name)
                        }
                    }
                }
                Row {
                    if (currentUser.avatar.shirt == null) {
                        Text("Рубашка: - ")
                    } else {
                        Text("Рубашка: ")
                        Column(modifier = Modifier.clickable { isItemDescription.value = currentUser.avatar.shirt  }) {
                            Image(
                                painter = painterResource(currentUser.avatar.shirt!!.iconId),
                                contentDescription = null
                            )
                            Text(currentUser.avatar.shirt!!.name)
                        }
                    }
                }
                Row {
                    if (currentUser.avatar.trousers == null) {
                        Text("Штаны: - ")
                    } else {
                        Text("Штаны: ")
                        Column(modifier = Modifier.clickable { isItemDescription.value = currentUser.avatar.trousers  }) {
                            Image(
                                painter = painterResource(currentUser.avatar.trousers!!.iconId),
                                contentDescription = null
                            )
                            Text(currentUser.avatar.trousers!!.name)
                        }
                    }
                }
                Row {
                    if (currentUser.avatar.shoes == null) {
                        Text("Обувь: - ")
                    } else {
                        Text("Обувь: ")
                        Column(modifier = Modifier.clickable { isItemDescription.value = currentUser.avatar.shoes  }) {
                            Image(
                                painter = painterResource(currentUser.avatar.shoes!!.iconId),
                                contentDescription = null
                            )
                            Text(currentUser.avatar.shoes!!.name)
                        }
                    }
                }
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth())  {
            Box(modifier = Modifier.clickable { scale.intValue = 1 }) {
                Text("Предметы", textDecoration = when(scale.intValue) {1 -> TextDecoration.Underline else -> null})
            }
            Box(modifier = Modifier.clickable { scale.intValue = 2 }) {
                Text("Кейсы", textDecoration = when(scale.intValue) {2 -> TextDecoration.Underline else -> null})
            }
            Box(modifier = Modifier.clickable { scale.intValue = 3 }) {
                Text("Достижения", textDecoration = when(scale.intValue) {3 -> TextDecoration.Underline else -> null})
            }
        }
        when (scale.intValue) {
            1 -> PrintInventory1()
            2 -> PrintInventory2()
            3 -> PrintAchievements()
        }
        if (isItemDescription.value != null) {
            PrintItemDescription(isItemDescription.value!!)
        }
    }




}

@Composable
fun PrintInventory1() {
    LazyVerticalGrid  (
        columns = GridCells.Adaptive(minSize = 120.dp)
    ) {
        for (el in currentUser.inventory1) {
            item {
                Box(modifier = Modifier.border(1.dp, color = Color.Gray),
                ) {
                    el.key.Print(
                        backgroundModifier = Modifier
                            .clickable{ isItemDescription.value = el.key }
                            .size(120.dp)
                    )
                    Row(modifier = Modifier.fillMaxWidth().align(Alignment.BottomEnd), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(el.key.name)
                        Text("${el.value}")
                    }
                }
            }
        }
    }
}

@Composable
fun PrintInventory2() {
    LazyVerticalGrid  (
        columns = GridCells.Adaptive(minSize = 120.dp)
    ) {
        for (el in currentUser.inventory2) {
            item {
                Column(modifier = Modifier.border(1.dp, color = Color.Gray).background(brush = Brush.radialGradient(
                    listOf(Color.DarkGray, Color.Gray)
                ))) {
                    Image(painter = painterResource(el.key.imageId), el.key.name)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(el.key.name)
                        Text("${el.value}")
                    }
                }
            }
        }
    }
}

@Composable
fun PrintAchievements() {
    // Text("\nУ вас их пока что нет)", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
    LazyVerticalGrid  (
        columns = GridCells.Adaptive(minSize = 120.dp)
    ) {
        for (el in currentUser.achievements) {
            item {
                Column(modifier = Modifier.border(1.dp, color = Color.Gray).background(
                    brush = Brush.radialGradient(listOf(Yellow, LightYellow))
                )) {
                    Image(painter = painterResource(el.key.imageId), el.key.name)
                    Text(el.key.name, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    Text("Полученно:\n${el.value}")
                }
            }
        }
    }
}

@Composable
fun PrintItemDescription(item: ItemData) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Описание предмета") },
        text = {
            LazyColumn {
                item {
                    item.Print()
                }
                item{
                    Text("Название:${item.name}")
                }
                item {
                    Text(
                        "Тип: ${
                            when (item.content::class.simpleName) {
                                "Hat" -> "Шапка"
                                "Shirt" -> "Рубашка"
                                "Trousers" -> "Штаны"
                                "Shoes" -> "Обувь"
                                else -> "error"
                            }
                        }"
                    )
                }
                item {
                    Row {
                        Text("Редкость: ")
                        Text(
                            when (item.rare) {
                                ItemData.ItemRare.Usual -> "Обычный"
                                ItemData.ItemRare.Rare -> "Редкий"
                                ItemData.ItemRare.UnRare -> "Супер редкий"
                                ItemData.ItemRare.Epic -> "Эпический"
                                ItemData.ItemRare.Mythic -> "Мифический"
                                ItemData.ItemRare.Legendary -> "Легендарный"
                                ItemData.ItemRare.Secret -> "Лимитированый"
                            },
                            color = when (item.rare) {
                                ItemData.ItemRare.Usual -> Color.Unspecified
                                ItemData.ItemRare.Rare -> Green
                                ItemData.ItemRare.UnRare -> Blue
                                ItemData.ItemRare.Epic -> Purple
                                ItemData.ItemRare.Mythic -> Red
                                ItemData.ItemRare.Legendary -> Yellow
                                ItemData.ItemRare.Secret -> Color.Cyan
                            }
                        )
                    }
                }

            }

        },
        confirmButton = {
            Button({ isItemDescription.value = null}) {
                Text("Закрыть")
            }
        }
    )
}
