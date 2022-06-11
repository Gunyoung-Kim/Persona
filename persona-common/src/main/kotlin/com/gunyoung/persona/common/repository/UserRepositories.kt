package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.model.UserEntity
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {
    fun findUserByTaliId(taliId: String): UserEntity?

    fun save(user: UserEntity): UserEntity
}