package com.gunyoung.persona.common.testutil

import com.gunyoung.persona.common.model.UserEntity
import com.gunyoung.persona.common.model.UserStatus
import java.time.LocalDate

fun sampleUserEntity(
    taliId: String = "test",
    firstName: String = "스트",
    lastName: String = "테",
    nickName: String = "닉네임",
    email: String = "test@tali.com",
    phoneNumber: String = "010-1234-5678",
    birthDate: LocalDate = LocalDate.of(1999, 1, 16),
    status: UserStatus = UserStatus.ACTIVE
): UserEntity =
    UserEntity(
        taliId, firstName, lastName, nickName, email, phoneNumber, birthDate, status
    )