package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.model.AlarmOptionEntity
import com.gunyoung.persona.common.model.QAlarmOptionEntity.alarmOptionEntity
import com.querydsl.jpa.JPAExpressions.select
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
interface AlarmOptionRepository : JpaRepository<AlarmOptionEntity, Long>, CustomizedAlarmOptionRepository

interface CustomizedAlarmOptionRepository {

    fun isSmsAlarmReceived(userId: Long): Boolean?

    fun updateSmsAlarmReceived(userId: Long, isReceived: Boolean): Long

    fun isMailAlarmReceived(userId: Long): Boolean?

    fun updateMailAlarmReceived(userId: Long, isReceived: Boolean): Long
}

@Repository
class CustomizedAlarmOptionQueryRepositoryImpl : QuerydslRepositorySupport(AlarmOptionEntity::class.java),
    CustomizedAlarmOptionRepository {

    override fun isSmsAlarmReceived(userId: Long): Boolean? =
        select(alarmOptionEntity.isSmsAlarmReceived)
            .from(alarmOptionEntity)
            .where(userIdEquals(userId))
            .fetchOne()

    override fun updateSmsAlarmReceived(userId: Long, isReceived: Boolean): Long =
        update(alarmOptionEntity)
            .set(alarmOptionEntity.isSmsAlarmReceived, isReceived)
            .where(userIdEquals(userId))
            .execute()

    override fun isMailAlarmReceived(userId: Long): Boolean? =
        select(alarmOptionEntity.isMailAlarmReceived)
            .from(alarmOptionEntity)
            .where(userIdEquals(userId))
            .fetchOne()

    override fun updateMailAlarmReceived(userId: Long, isReceived: Boolean): Long =
        update(alarmOptionEntity)
            .set(alarmOptionEntity.isMailAlarmReceived, isReceived)
            .where(userIdEquals(userId))
            .execute()

    private fun userIdEquals(userId: Long) = alarmOptionEntity.user.id.eq(userId)

}