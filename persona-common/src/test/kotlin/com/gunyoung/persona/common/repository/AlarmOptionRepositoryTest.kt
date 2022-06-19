package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.config.CommonConfig
import com.gunyoung.persona.common.model.AlarmOptionEntity
import com.gunyoung.persona.common.model.UserEntity
import com.gunyoung.persona.common.testutil.sampleAlarmOptionEntity
import com.gunyoung.persona.common.testutil.sampleUserEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

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
        println("hello world")
    }

    @Test
    fun test() {
        sampleUser = sampleUserEntity()
        userRepository.save(sampleUser)

        sampleAlarmOption = sampleAlarmOptionEntity()
        sampleAlarmOption.user = sampleUser
        alarmOptionRepository.save(sampleAlarmOption)
    }

}
