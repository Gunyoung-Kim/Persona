package com.gunyoung.persona.common.model

data class PersonaErrorResponse(
    val statusCode: Int,
    val exception: Throwable
)