package com.gunyoung.persona.common.testutil

import com.gunyoung.persona.common.model.User

fun sampleUser(
    userId: Long = 1L,
    taliId: String = "test",
    firstName: String = "스트",
    lastName: String = "테",
    email: String = "test@tali.com",
    phoneNumber: String = "010-1234-5678"
): User =
    User(userId, taliId, firstName, lastName, email, phoneNumber)