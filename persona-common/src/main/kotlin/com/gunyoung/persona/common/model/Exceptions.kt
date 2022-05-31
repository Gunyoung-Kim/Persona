package com.gunyoung.persona.common.model

open class PersonaException : RuntimeException()

open class NotFoundException : PersonaException()
class UserNotFoundException : NotFoundException()