package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.model.AlarmOptionEntity
import com.gunyoung.persona.common.model.UserEntity
import com.gunyoung.persona.common.testutil.sampleAlarmOptionEntity
import com.gunyoung.persona.common.testutil.sampleUserEntity
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class AlarmOptionRepositoryTest(
    @Autowired
    private val alarmOptionRepository: AlarmOptionRepository
) {

    private lateinit var sampleUser: UserEntity

    private lateinit var sampleAlarmOption: AlarmOptionEntity

    @BeforeEach
    fun setUp() {
        sampleUser = sampleUserEntity()
        sampleAlarmOption = sampleAlarmOptionEntity()
    }
}