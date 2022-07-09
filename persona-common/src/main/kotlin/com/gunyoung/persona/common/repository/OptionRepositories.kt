package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.model.AlarmOptionEntity
import com.gunyoung.persona.common.model.QAlarmOptionEntity.alarmOptionEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface AlarmOptionRepository : JpaRepository<AlarmOptionEntity, Long>, CustomizedAlarmOptionRepository

interface CustomizedAlarmOptionRepository {

    fun isSmsAlarmReceived(userId: Long): Boolean?

    fun updateSmsAlarmReceived(userId: Long, isReceived: Boolean): Long

    fun isMailAlarmReceived(userId: Long): Boolean?

    fun updateMailAlarmReceived(userId: Long, isReceived: Boolean): Long
}

@Repository
class CustomizedAlarmOptionRepositoryImpl(
    val queryFactory: JPAQueryFactory
) : CustomizedAlarmOptionRepository {

    override fun isSmsAlarmReceived(userId: Long): Boolean? =
        queryFactory.select(alarmOptionEntity.isSmsAlarmReceived)
            .from(alarmOptionEntity)
            .where(userIdEquals(userId))
            .fetchOne()

    @Transactional
    override fun updateSmsAlarmReceived(userId: Long, isReceived: Boolean): Long =
        queryFactory.update(alarmOptionEntity)
            .set(alarmOptionEntity.isSmsAlarmReceived, isReceived)
            .where(userIdEquals(userId))
            .execute()

    override fun isMailAlarmReceived(userId: Long): Boolean? =
        queryFactory.select(alarmOptionEntity.isMailAlarmReceived)
            .from(alarmOptionEntity)
            .where(userIdEquals(userId))
            .fetchOne()

    @Transactional
    override fun updateMailAlarmReceived(userId: Long, isReceived: Boolean): Long =
        queryFactory.update(alarmOptionEntity)
            .set(alarmOptionEntity.isMailAlarmReceived, isReceived)
            .where(userIdEquals(userId))
            .execute()

    private fun userIdEquals(userId: Long) = alarmOptionEntity.user.id.eq(userId)

}
