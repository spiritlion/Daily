package com.example.daily.custom

import java.time.LocalDate

var testData = LocalDate.now()

var users = listOf(
    UserData(
        nickname = "User1",
        avatar = AvatarData(
            hat = tester2,
            shirt = tester3,
            trousers = tester4,
            shoes = tester5
        ),
        inventory1 = mutableMapOf(
            tester2 to 2,
            tester3 to 5,
            tester4 to 7,
            tester5 to 1,
            tester6 to 3,
            tester7 to 4,
            tester8 to 999
        ),
        inventory2 =  mutableMapOf(testerCase1 to 10),
        achievements = mutableMapOf(
            testA1 to testData
        )
    )
)