package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.SpringBootTestConfig
import com.gunyoung.persona.common.model.UserEntity
import com.gunyoung.persona.common.model.UserStatus
import com.gunyoung.persona.common.testutil.sampleUserEntity
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@SpringBootTest
@Import(SpringBootTestConfig::class)
class UserRepositoryTest(
    @Autowired
    private val userRepository: UserRepository
) {

    private lateinit var sampleUser: UserEntity

    @BeforeEach
    fun setUp() {
        assertEquals(0, userRepository.count())

        sampleUser = sampleUserEntity()
        userRepository.save(sampleUser)
    }

    @AfterEach
    fun tearDown() {
        userRepository.delete(sampleUser)
    }

    @Test
    @Transactional
    fun `Tali ID 를 통해 UserEntity 를 조회할 때 Deleted 유저는 조회하지 않는다`() {
        // given
        val deletedUserTaliId = "deleteduser"
        val deletedUser = sampleUserEntity(
            taliId = deletedUserTaliId,
            email = "delete@test.com",
            status = UserStatus.DELETED
        )
        userRepository.save(deletedUser)

        // when
        val result = userRepository.findUserByTaliId(deletedUserTaliId)

        // then
        assertNull(result)
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

    @Test
    @Transactional
    fun `Tali Id 를 통해 유저 삭제 처리를 한다`() {
        // given
        val taliId = sampleUser.taliId

        // when
        userRepository.deleteUserByTaliId(taliId)

        // then
        assertEquals(0, userRepository.count())
    }
}
