package com.example.daily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    Main(modifier = Modifier.padding(it))
                }
            }
        }
    }
}
@Composable
fun Main(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Column(modifier) {

        NavBar(navController = navController)
    }
}
@Composable
fun NavBar(navController: NavHostController){
    val items = listOf("Daily", "Магазин", "Инвентарь", "Настройки")
    val selectedItem = remember { mutableStateOf(items[0]) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet{
                items.forEach { item ->
                    TextButton(
                        onClick = {
                            scope.launch { drawerState.close() }
                            when (item) {
                                "Daily" -> { navController.navigate(NavRoutes.Main.route) }
                                "Магазин" -> { navController.navigate(NavRoutes.Shop.route) }
                                "Инвентарь" -> { navController.navigate(NavRoutes.Inventory.route) }
                                "Настройки" -> { navController.navigate(NavRoutes.Setting.route) }
                            }
                            selectedItem.value = item
                        },
                    ) {
                        Row {
                            if (item == "Daily") {
                                Text("Daily",
                                    fontSize = 40.sp,
                                    modifier = Modifier.padding(horizontal = 40.dp),
                                    color = Yellow,
                                    fontStyle = FontStyle.Italic
                                )
                            } else {
                                Text(item, fontSize = 22.sp)
                            }
                        }
                    }
                }
            }
        },
        content = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }, content = {
                            Icon(Icons.Filled.Menu, "Меню")
                        })
                        Text(when (selectedItem.value) {
                            "Daily" -> "Добро пожаловать в Daily"
                            else -> selectedItem.value
                        }, fontSize = 30.sp)
                    }
                    if (selectedItem.value != "Daily" && selectedItem.value != "Настройки") Text("Ваш баланс: ${currentUser.moneys}")
                }
                NavHost(navController, startDestination = NavRoutes.Main.route) {
                    composable(NavRoutes.Main.route) { MainMenuScreen(scope, drawerState) }
                    composable(NavRoutes.Shop.route) { ShopScreen()  }
                    composable(NavRoutes.Inventory.route) { InventoryScreen() }
                    composable(NavRoutes.Setting.route) { SettingsScreen() }
                }
            }

        }
    )
}




sealed class NavRoutes(val route: String) {
    data object Main : NavRoutes("main")
    data object Shop : NavRoutes("shop")
    data object Inventory : NavRoutes("inventory")
    data object Setting : NavRoutes("setting")
}
