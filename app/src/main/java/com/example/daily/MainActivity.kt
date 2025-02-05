package com.example.daily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.daily.custom.screens.CaseScreen
import com.example.daily.custom.screens.InventoryScreen
import com.example.daily.custom.testerCase
import com.example.daily.custom.users
import com.example.daily.ui.theme.DailyTheme

var currentUser = users[0]
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyTheme {
                Scaffold {
                    Column(modifier = Modifier.padding(it)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Button( {} ) {
                                Image(painter = painterResource(R.drawable.account_image), "Account", Modifier.size(30.dp))
                            }
                            Button( {} ) {
                                Image(painter = painterResource(R.drawable.settings_image), "setting", Modifier.size(30.dp))
                            }
                        }
                        InventoryScreen()
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