package com.gunyoung.persona.common.service

import com.gunyoung.persona.common.model.UserEntity
import com.gunyoung.persona.common.model.UserNotFoundException
import com.gunyoung.persona.common.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun findUserByTaliId(taliId: String): UserEntity =
        userRepository.findUserByTaliId(taliId) ?: throw UserNotFoundException()

    fun modifyPhoneNumber(taliId: String, updatedPhoneNumber: String): UserEntity =
        findUserByTaliId(taliId)
            .updatePhoneNumber(updatedPhoneNumber)
            .save()

    private fun UserEntity.updatePhoneNumber(updatedPhoneNumber: String): UserEntity =
        copy(phoneNumber = updatedPhoneNumber)

    private fun UserEntity.save(): UserEntity = userRepository.save(this)
}
