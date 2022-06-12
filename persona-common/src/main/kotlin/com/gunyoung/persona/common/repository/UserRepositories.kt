package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.model.QUserEntity.userEntity
import com.gunyoung.persona.common.model.UserEntity
import com.querydsl.jpa.JPAExpressions.selectFrom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long>, CustomizedUserRepository

interface CustomizedUserRepository {
    fun findUserByTaliId(taliId: String): UserEntity?
}

@Repository
class CustomizedUserRepositoryImpl : QuerydslRepositorySupport(UserEntity::class.java),
    CustomizedUserRepository {

    override fun findUserByTaliId(taliId: String): UserEntity? =
        selectFrom(userEntity)
            .where(userEntity.taliId.eq(taliId))
            .fetchOne()
}

@Repository
interface UserIdMappingRepository {
    fun findTaliIdById(id: Long): String?

    fun findIdByTaliId(taliId: String): Long?
}
