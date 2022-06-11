package com.gunyoung.persona.api.model

data class UpdatePhoneNumberRequest(
    val taliId: String,
    val phoneNumber: String
)