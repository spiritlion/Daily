package com.example.daily.custom

import java.time.LocalDate

data class UserData(
    var nickname: String,
    var moneys : Float,
    var avatar: AvatarData = AvatarData(),
    var inventory1: MutableMap<ItemData, Int> = mutableMapOf(),
    var inventory2: MutableMap<CaseData, Int> = mutableMapOf(),
    var achievements: MutableMap<AchievementData, LocalDate> = mutableMapOf()
)
