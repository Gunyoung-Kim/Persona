package com.gunyoung.persona.common.service

import com.gunyoung.persona.common.repository.AlarmOptionRepository
import org.springframework.stereotype.Service

@Service
class AlarmOptionService(
    private val alarmOptionRepository: AlarmOptionRepository
) {

    fun isPhoneAlarmReceived(taliId: String): Boolean =
        alarmOptionRepository.isPhoneAlarmReceived(taliId)

    fun updatePhoneAlarmReceived(taliId: String, isReceived: Boolean): Boolean =
        alarmOptionRepository.updatePhoneAlarmReceived(taliId, isReceived)

    fun isMailAlarmReceived(taliId: String): Boolean =
        alarmOptionRepository.isMailAlarmReceived(taliId)

    fun updateMailAlarmReceived(taliId: String, isReceived: Boolean): Boolean =
        alarmOptionRepository.updateMailAlarmReceived(taliId, isReceived)
}