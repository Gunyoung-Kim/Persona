package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.model.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {
    fun findUserByTaliId(taliId: String): User?

    fun save(user: User): User
}