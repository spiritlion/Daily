package com.example.daily.custom

data class CaseData(
    var imageId : Int,
    var name : String,
    var description : String,
    var price : Float,
    var content : MutableMap<ItemData, Float>
)
