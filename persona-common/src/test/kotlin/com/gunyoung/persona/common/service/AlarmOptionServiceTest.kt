package com.gunyoung.persona.common.service

import com.gunyoung.persona.common.model.UserNotFoundException
import com.gunyoung.persona.common.repository.AlarmOptionRepository
import com.gunyoung.persona.common.repository.UserIdMappingRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class AlarmOptionServiceUnitTest {

    @MockK
    lateinit var alarmOptionRepository: AlarmOptionRepository

    @MockK
    lateinit var userIdMappingRepository: UserIdMappingRepository

    @InjectMockKs
    lateinit var alarmOptionService: AlarmOptionService

    private val sampleTaliId = "gun025bba"
    private val sampleUserId = 1L

    @Test
    fun `taliId 로 유저 sms 알림 수신 여부를 확인할 때 taliId 에 해당하는 유저가 없으면 UserNotFoundException 을 던진다`() {
        // given
        val nonExistTaliId = "nonexist"
        userIdMappingRepository.stubFindIdByTaliIdReturnNull(nonExistTaliId)

        // when, then
        assertThrows<UserNotFoundException> {
            alarmOptionService.isSmsAlarmReceived(nonExistTaliId)
        }
    }

    @Test
    fun `taliId 로 유저 sms 알림 수신 여부를 확인한다`() {
        // given
        val isSmsReceived = true
        userIdMappingRepository.stubFindIdByTaliIdWithSampleIds()
        every { alarmOptionRepository.isSmsAlarmReceived(sampleUserId) } returns isSmsReceived

        // when
        val result = alarmOptionService.isSmsAlarmReceived(sampleTaliId)

        // then
        assertEquals(isSmsReceived, result)
    }

    @Test
    fun `taliId 로 유저 sms 알림 수신 여부를 수정할 때 taliId 에 해당하는 유저가 없으면 UserNotFoundException 을 던진다`() {
        // given
        val nonExistTaliId = "nonexist"
        userIdMappingRepository.stubFindIdByTaliIdReturnNull(nonExistTaliId)

        val newIsReceived = false

        // when, then
        assertThrows<UserNotFoundException> {
            alarmOptionService.updateSmsAlarmReceived(nonExistTaliId, newIsReceived)
        }
    }

    @Test
    fun `taliId 로 유저 sms 알림 수신 여부를 수정한다`() {
        // given
        val newIsReceived = true
        userIdMappingRepository.stubFindIdByTaliIdWithSampleIds()

        val effectedRow = 1L

        every { alarmOptionRepository.updateSmsAlarmReceived(sampleUserId, newIsReceived) } returns effectedRow

        // when
        val result = alarmOptionService.updateSmsAlarmReceived(sampleTaliId, newIsReceived)

        // then
        assertEquals(effectedRow, result)
    }

    @Test
    fun `taliId 로 유저 이메일 알림 수신 여부를 확인할 때 taliId 에 해당하는 유저가 없으면 UserNotFoundException 을 던진다`() {
        // given
        val nonExistTaliId = "nonexist"
        userIdMappingRepository.stubFindIdByTaliIdReturnNull(nonExistTaliId)

        // when, then
        assertThrows<UserNotFoundException> {
            alarmOptionService.isMailAlarmReceived(nonExistTaliId)
        }
    }

    @Test
    fun `taliId 로 유저 이메일 알림 수신 여부를 확인한다`() {
        // given
        val isMailReceived = true
        userIdMappingRepository.stubFindIdByTaliIdWithSampleIds()
        every { alarmOptionRepository.isMailAlarmReceived(sampleUserId) } returns isMailReceived

        // when
        val result = alarmOptionService.isMailAlarmReceived(sampleTaliId)

        // then
        assertEquals(isMailReceived, result)
    }

    @Test
    fun `taliId 로 유저 이메일 알림 수신 여부를 수정할 때 taliId 에 해당하는 유저가 없으면 UserNotFoundException 을 던진다`() {
        // given
        val nonExistTaliId = "nonexist"
        userIdMappingRepository.stubFindIdByTaliIdReturnNull(nonExistTaliId)

        val newIsReceived = false

        // when, then
        assertThrows<UserNotFoundException> {
            alarmOptionService.updateMailAlarmReceived(nonExistTaliId, newIsReceived)
        }
    }

    @Test
    fun `taliId 로 유저 이메일 알림 수신 여부를 수정한다`() {
        // given
        val newIsReceived = true
        userIdMappingRepository.stubFindIdByTaliIdWithSampleIds()

        val effectedRow = 1L

        every { alarmOptionRepository.updateMailAlarmReceived(sampleUserId, newIsReceived) } returns effectedRow

        // when
        val result = alarmOptionService.updateMailAlarmReceived(sampleTaliId, newIsReceived)

        // then
        assertEquals(effectedRow, result)
    }

    private fun UserIdMappingRepository.stubFindIdByTaliIdReturnNull(nonExistTaliId: String) {
        every { this@stubFindIdByTaliIdReturnNull.findIdByTaliId(nonExistTaliId) } returns null
    }

    private fun UserIdMappingRepository.stubFindIdByTaliIdWithSampleIds() {
        every { this@stubFindIdByTaliIdWithSampleIds.findIdByTaliId(sampleTaliId) } returns sampleUserId
    }
}