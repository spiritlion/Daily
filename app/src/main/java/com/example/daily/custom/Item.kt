package com.example.daily.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.daily.ui.theme.Blue
import com.example.daily.ui.theme.Green
import com.example.daily.ui.theme.LightBlue
import com.example.daily.ui.theme.LightGreen
import com.example.daily.ui.theme.LightPurple
import com.example.daily.ui.theme.LightRed
import com.example.daily.ui.theme.Purple
import com.example.daily.ui.theme.Red
import com.example.daily.ui.theme.Yellow

data class ItemData(
    var iconId : Int,
    var name : String,
    var description : String,
    var content: ItemContent,
    var rare : ItemRare = ItemRare.Usual
) {
    open class ItemContent {
        data class Money(
            var count : Float
        ) : ItemContent()
        class Hat : ItemContent()
        class Shirt : ItemContent()
        class Trousers : ItemContent()
        class Shoes : ItemContent()
    }

    enum class ItemRare {
        Usual,
        Rare,
        UnRare,
        Epic,
        Mythic,
        Legendary,

        Secret
    }
}

@Composable
fun ItemData.Print(backgroundModifier : Modifier = Modifier, foregroundModifier : Modifier = Modifier) {
    val boxColor = when (rare) {
        ItemData.ItemRare.Usual -> listOf(Color.DarkGray, Color.Gray)
        ItemData.ItemRare.Rare -> listOf(Green, LightGreen)
        ItemData.ItemRare.UnRare -> listOf(Blue, LightBlue)
        ItemData.ItemRare.Epic -> listOf(Purple, LightPurple)
        ItemData.ItemRare.Mythic -> listOf(Red, LightRed)
        ItemData.ItemRare.Legendary -> listOf(LightRed, Yellow)
        ItemData.ItemRare.Secret -> listOf(LightGreen, LightBlue)
    }
    Column(modifier = backgroundModifier
        .background(brush = Brush.radialGradient(boxColor))) {
        Image(painter = painterResource(iconId), name)
    }
}
