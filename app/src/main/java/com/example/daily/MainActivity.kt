package com.example.daily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.daily.custom.screens.InventoryScreen
import com.example.daily.custom.screens.MainMenuScreen
import com.example.daily.custom.screens.SettingsScreen
import com.example.daily.custom.screens.ShopScreen
import com.example.daily.custom.users
import com.example.daily.ui.theme.DailyTheme
import com.example.daily.ui.theme.Yellow
import kotlinx.coroutines.launch

var currentUser = users[0]
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyTheme {
                Scaffold {
                    val items = listOf("Главный экран", "Магазин", "Инвентарь", "Настройки")
                    val selectedItem = remember { mutableStateOf(items[0]) }
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet {
                                Text("Daily",
                                    fontSize = 40.sp,
                                    modifier = Modifier.padding(horizontal = 40.dp),
                                    color = Yellow,
                                    fontStyle = FontStyle.Italic
                                )
                                items.forEach { item ->
                                    TextButton(onClick = {
                                        scope.launch { drawerState.close() }
                                        selectedItem.value = item
                                    },
                                        colors = ButtonDefaults.buttonColors(contentColor = Color.LightGray, containerColor = Color.Transparent)) {
                                        Text(item, fontSize = 22.sp)
                                    }
                                }
                            }
                        },
                        scrimColor = Color(0xC9000000),
                        modifier = Modifier.padding(it)
                    ) {
                        Column {
                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {
                                    scope.launch {drawerState.open()}
                                }, content = {
                                    Icon(Icons.Filled.Menu, "Меню")
                                })
                                Text(selectedItem.value, fontSize = 30.sp)
                            }

                            when (selectedItem.value) {
                                "Главный экран" -> MainMenuScreen()
                                "Магазин" -> ShopScreen()
                                "Инвентарь" -> InventoryScreen()
                                "Настройки" -> SettingsScreen()
                                else -> {
                                    Text(
                                        "Ошибка, такого экрана не существует",
                                        color = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DailyTheme {
        InventoryScreen()
    }
}