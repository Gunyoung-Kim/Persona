package com.gunyoung.persona.common.service

import com.gunyoung.persona.common.model.AlarmOptionNotFoundException
import com.gunyoung.persona.common.model.UserNotFoundException
import com.gunyoung.persona.common.repository.AlarmOptionRepository
import com.gunyoung.persona.common.repository.UserIdMappingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AlarmOptionService(
    private val alarmOptionRepository: AlarmOptionRepository,
    private val userIdMappingRepository: UserIdMappingRepository
) {

    fun isSmsAlarmReceived(taliId: String): Boolean =
        convertTaliIdToUserId(taliId).let { userId ->
            alarmOptionRepository.isSmsAlarmReceived(userId)
        } ?: throw AlarmOptionNotFoundException()

    @Transactional
    fun updateSmsAlarmReceived(taliId: String, isReceived: Boolean): Long =
        convertTaliIdToUserId(taliId).let { userId ->
            alarmOptionRepository.updateSmsAlarmReceived(userId, isReceived)
        }

    fun isMailAlarmReceived(taliId: String): Boolean =
        convertTaliIdToUserId(taliId).let { userId ->
            alarmOptionRepository.isMailAlarmReceived(userId)
        } ?: throw AlarmOptionNotFoundException()

    @Transactional
    fun updateMailAlarmReceived(taliId: String, isReceived: Boolean): Long =
        convertTaliIdToUserId(taliId).let { userId ->
            alarmOptionRepository.updateMailAlarmReceived(userId, isReceived)
        }

    private fun convertTaliIdToUserId(taliId: String): Long =
        userIdMappingRepository.findIdByTaliId(taliId) ?: throw UserNotFoundException()
}