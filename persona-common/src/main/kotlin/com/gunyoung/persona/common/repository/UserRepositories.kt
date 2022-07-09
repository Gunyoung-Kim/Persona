package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.model.QUserEntity.userEntity
import com.gunyoung.persona.common.model.UserEntity
import com.gunyoung.persona.common.model.UserStatus
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long>, CustomizedUserRepository

interface CustomizedUserRepository {
    fun findUserByTaliId(taliId: String): UserEntity?

    fun deleteUserByTaliId(taliId: String): Long
}

@Repository
class CustomizedUserRepositoryImpl(
    val queryFactory: JPAQueryFactory
) : CustomizedUserRepository {

    override fun findUserByTaliId(taliId: String): UserEntity? =
        queryFactory.selectFrom(userEntity)
            .where(
                userEntity.taliId.eq(taliId)
                    .andNotDeleted()
            ).fetchOne()

    override fun deleteUserByTaliId(taliId: String): Long =
        queryFactory.update(userEntity)
            .set(userEntity.status, UserStatus.DELETED)
            .where(userEntity.taliId.eq(taliId))
            .execute()

    private fun BooleanExpression.andNotDeleted(): BooleanExpression =
        this.and(userEntity.status.ne(UserStatus.DELETED))
}

interface UserIdMappingRepository {
    fun findTaliIdById(id: Long): String?

    fun findIdByTaliId(taliId: String): Long?
}

@Repository
class UserIdMappingRepositoryImpl(
    private val redisTemplate: RedisTemplate<String, Long>
) : UserIdMappingRepository {
    override fun findTaliIdById(id: Long): String? {
        TODO("Not yet implemented")
    }

    override fun findIdByTaliId(taliId: String): Long? {
        TODO("Not yet implemented")
    }

}
