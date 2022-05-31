package com.gunyoung.persona.common.controller

import com.gunyoung.persona.common.model.PersonaErrorResponse
import com.gunyoung.persona.common.model.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class PersonaExceptionController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(e: UserNotFoundException) =
        PersonaErrorResponse(HttpStatus.NOT_FOUND.value(), e)
}