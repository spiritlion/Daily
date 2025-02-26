package com.example.daily.custom.screens

import android.provider.ContactsContract.Data
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.daily.custom.CaseData
import com.example.daily.custom.Print
import com.example.daily.custom.testerCase1
import com.example.daily.custom.testerCase2
import com.example.daily.custom.testerCase3

@Composable
fun ShopScreen(modifier: Modifier = Modifier) {
    var state by remember { mutableIntStateOf(1) }
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Box(modifier = Modifier.clickable { state = 1 }) {
                Text("Обычные акции", textDecoration = when(state) {1 -> TextDecoration.Underline else -> null})
            }
            Box(modifier = Modifier.clickable { state = 2 }) {
                Text("Сезоные акции", textDecoration = when(state) {2 -> TextDecoration.Underline else -> null})
            }
        }
        when (state) {
            1 -> {
                LazyColumn {
                    item{
                        Spacer(modifier = Modifier.padding(7.dp))
                        Series("Серия обуви", listOf(
                            listOf(testerCase1, testerCase2),
                            listOf(testerCase3)
                        ))
                    }
                    item {
                        Spacer(modifier = Modifier.padding(7.dp))
                        Series("Серия головных уборов", listOf(
                            listOf(testerCase1, testerCase2),
                            listOf(testerCase3)
                        ))
                    }
                }
            }
            2 -> {
                Text("Пока что не один сезон не идёт")
            }
        }
    }
}

@Composable
fun Series(name : String, content : List<List<CaseData>>) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(name, fontSize = 30.sp)
        Spacer(modifier = Modifier.padding(7.dp))
        for (i in content.indices) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                for (j in content[i].indices) {
                    content[i][j].Print(modifier = Modifier.size(180.dp))
                }
            }
        }
    }
}