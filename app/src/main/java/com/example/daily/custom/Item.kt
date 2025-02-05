package com.example.daily.custom

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
