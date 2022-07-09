package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.SpringBootTestConfig
import com.gunyoung.persona.common.model.UserEntity
import com.gunyoung.persona.common.testutil.sampleUserEntity
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest
@Import(SpringBootTestConfig::class)
class UserRepositoryTest(
    @Autowired
    private val userRepository: UserRepository
) {

    private lateinit var sampleUser: UserEntity

    @BeforeEach
    fun setUp() {
        sampleUser = sampleUserEntity()
        userRepository.save(sampleUser)
    }

    @AfterEach
    fun tearDown() {
        userRepository.deleteAll()
    }

    @Test
    fun `Tali ID 를 통해 UserEntity 를 조회한다`() {
        // given
        val taliId = sampleUser.taliId
        val userId = sampleUser.id

        // when
        val result = userRepository.findUserByTaliId(taliId)

        // then
        assertNotNull(result)
        assertEquals(userId, result.id)
    }
}
