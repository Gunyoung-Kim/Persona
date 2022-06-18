package com.gunyoung.persona.api.model

import com.gunyoung.persona.common.model.UserStatus
import java.time.LocalDate

data class UserPhoneNumberResponse(
    val taliId: String,
    val phoneNumber: String
)

data class UserResponse(
    val userId: Long,
    val taliId: String,
    val firstName: String,
    val lastName: String,
    val nickName: String,
    val email: String,
    val phoneNumber: String,
    val birthDate: LocalDate,
    val status: UserStatus
)

data class UserAndAlarmOptionResponse(
    val user: UserResponse,
    val alarmOption: AlarmOptionResponse
)

data class AlarmOptionResponse(
    val isSmsAlarmReceived: Boolean,
    val isMailAlarmReceived: Boolean
)
