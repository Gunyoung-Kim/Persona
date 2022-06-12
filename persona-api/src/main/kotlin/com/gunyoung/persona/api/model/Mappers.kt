package com.gunyoung.persona.api.model

import com.gunyoung.persona.common.model.NON_EXIST_ID
import com.gunyoung.persona.common.model.UserEntity

/**
 *  현재는 굳이 mapstruct 쓰지 않아도 될듯 2022.06.12
 */

fun UserEntity.createUserResponse(): UserResponse =
    UserResponse(
        userId = this.id ?: NON_EXIST_ID,
        taliId = this.taliId,
        firstName = this.firstName,
        lastName = this.lastName,
        nickName = this.nickName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        birthDate = this.birthDate,
        status = this.status
    )

fun UserEntity.createPhoneNumberResponse(): UserPhoneNumberResponse =
    UserPhoneNumberResponse(
        taliId = this.taliId,
        phoneNumber = this.phoneNumber
    )
