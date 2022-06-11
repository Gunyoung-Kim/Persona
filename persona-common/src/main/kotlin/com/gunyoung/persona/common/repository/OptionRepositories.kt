package com.gunyoung.persona.common.repository

import org.springframework.stereotype.Repository

@Repository
interface AlarmOptionRepository {
    fun isSmsAlarmReceived(userId: Long): Boolean

    fun updateSmsAlarmReceived(userId: Long, isReceived: Boolean): Boolean

    fun isMailAlarmReceived(userId: Long): Boolean

    fun updateMailAlarmReceived(userId: Long, isReceived: Boolean): Boolean
}