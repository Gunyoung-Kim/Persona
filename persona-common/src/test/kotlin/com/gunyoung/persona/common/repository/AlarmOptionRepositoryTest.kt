package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.config.CommonConfig
import com.gunyoung.persona.common.model.AlarmOptionEntity
import com.gunyoung.persona.common.model.UserEntity
import com.gunyoung.persona.common.testutil.sampleAlarmOptionEntity
import com.gunyoung.persona.common.testutil.sampleUserEntity
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals

@SpringBootTest
@SpringBootApplication
@Import(CommonConfig::class)
class AlarmOptionRepositoryTest(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val alarmOptionRepository: AlarmOptionRepository
) {

    private lateinit var sampleUser: UserEntity

    private lateinit var sampleAlarmOption: AlarmOptionEntity

    @BeforeEach
    fun setUp() {
        sampleUser = sampleUserEntity()
        userRepository.save(sampleUser)

        sampleAlarmOption = sampleAlarmOptionEntity()
        sampleAlarmOption.user = sampleUser
        alarmOptionRepository.save(sampleAlarmOption)
    }

    @AfterEach
    fun tearDown() {
        alarmOptionRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun `Sms 알람 수신 여부를 확인한다`() {
        // given
        val isSmsAlarmReceived = sampleAlarmOption.isSmsAlarmReceived
        val userId = sampleUser.id!!
        // when
        val result = alarmOptionRepository.isSmsAlarmReceived(userId)

        // then
        assertEquals(isSmsAlarmReceived, result)
    }

    @Test
    fun `Sms 알람 수신 여부를 수정한다`() {
        // given
        val existIsSmsAlarmReceived = sampleAlarmOption.isSmsAlarmReceived
        val userId = sampleUser.id!!

        val newIsSmsAlarmReceived = !existIsSmsAlarmReceived

        // when
        val result = alarmOptionRepository.updateSmsAlarmReceived(userId, newIsSmsAlarmReceived)

        // then
        assertEquals(1L, result)
        assertEquals(newIsSmsAlarmReceived, alarmOptionRepository.isSmsAlarmReceived(userId))
    }

    @Test
    fun `Mail 알람 수신 여부를 확인한다`() {
        // given
        val isMailAlarmReceived = sampleAlarmOption.isMailAlarmReceived
        val userId = sampleUser.id!!

        // when
        val result = alarmOptionRepository.isMailAlarmReceived(userId)

        // then
        assertEquals(isMailAlarmReceived, result)
    }

    @Test
    fun `Mail 알람 수신 여부를 수정한다`() {
        // given
        val existIsMailAlarmReceived = sampleAlarmOption.isMailAlarmReceived
        val userId = sampleUser.id!!

        val newIsMailAlarmReceived = !existIsMailAlarmReceived

        // when
        val result = alarmOptionRepository.updateMailAlarmReceived(userId, newIsMailAlarmReceived)

        // then
        assertEquals(newIsMailAlarmReceived, alarmOptionRepository.isMailAlarmReceived(userId))
    }

}
