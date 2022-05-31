package com.gunyoung.persona.common.service

import com.gunyoung.persona.common.model.User
import com.gunyoung.persona.common.model.UserNotFoundException
import com.gunyoung.persona.common.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun findUserByTaliId(taliId: String) : User =
        userRepository.findUserByTaliId(taliId) ?: throw UserNotFoundException()
}