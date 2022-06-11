package com.gunyoung.persona.common.repository

import org.springframework.stereotype.Repository

@Repository
interface AlarmOptionRepository {
    fun isPhoneAlarmReceived(taliId: String): Boolean

    fun updatePhoneAlarmReceived(taliId: String, isReceived: Boolean): Boolean

    fun isMailAlarmReceived(taliId: String): Boolean

    fun updateMailAlarmReceived(taliId: String, isReceived: Boolean): Boolean
}