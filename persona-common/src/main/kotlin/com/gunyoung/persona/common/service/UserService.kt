package com.gunyoung.persona.common.service

import com.gunyoung.kommon.common.util.notReturn
import com.gunyoung.persona.common.model.UserEntity
import com.gunyoung.persona.common.model.UserNotFoundException
import com.gunyoung.persona.common.repository.UserIdMappingRepository
import com.gunyoung.persona.common.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userIdMappingRepository: UserIdMappingRepository
) {

    fun findUserByTaliId(taliId: String): UserEntity =
        userRepository.findUserByTaliId(taliId) ?: throw UserNotFoundException()

    fun findUserIdByTaliId(taliId: String): Long =
        userIdMappingRepository.findIdByTaliId(taliId) ?: throw UserNotFoundException()

    fun findTaliIdByUserId(userId: Long): String =
        userIdMappingRepository.findTaliIdById(userId) ?: throw UserNotFoundException()

    @Transactional
    fun updatePhoneNumber(taliId: String, updatedPhoneNumber: String): UserEntity =
        findUserByTaliId(taliId)
            .updatePhoneNumber(updatedPhoneNumber)
            .save()

    private fun UserEntity.updatePhoneNumber(updatedPhoneNumber: String): UserEntity = apply {
        this.phoneNumber = updatedPhoneNumber
    }

    private fun UserEntity.save(): UserEntity = userRepository.save(this)

    @Transactional
    fun deleteUserByTaliId(taliId: String) =
        userRepository.deleteUserByTaliId(taliId).notReturn()
}
