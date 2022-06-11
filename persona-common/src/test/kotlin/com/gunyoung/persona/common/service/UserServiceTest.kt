package com.gunyoung.persona.common.service

import com.gunyoung.persona.common.model.UserEntity
import com.gunyoung.persona.common.model.UserNotFoundException
import com.gunyoung.persona.common.repository.UserRepository
import com.gunyoung.persona.common.testutil.sampleUser
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@ExtendWith(MockKExtension::class)
internal class UserServiceUnitTest {

    @MockK
    lateinit var userRepository: UserRepository

    @InjectMockKs
    lateinit var userService: UserService

    @Test
    fun `Tali Id 를 통해 User 를 반환할 때 존재하지 않는다면 UserNotFoundException 을 던진다`() {
        // given
        val nonExistTaliId = "nonexist"
        every { userRepository.findUserByTaliId(nonExistTaliId) } returns null

        // when, then
        assertThrows<UserNotFoundException> {
            userService.findUserByTaliId(nonExistTaliId)
        }
    }

    @Test
    fun `Tali Id 를 통해 User 를 반환한다`() {
        // given
        val taliId = "gun025bba"
        val sampleUser = userRepository.stubFindByTaliIdReturnSampleUser(taliId)

        // when
        val result = userService.findUserByTaliId(taliId)

        // then
        assertEquals(sampleUser, result)
    }

    @Test
    fun `유저의 전화번호를 업데이트 할때 존재하지 않는 유저라면 UserNotFoundException 을 던진다`() {
        // given
        val nonExistTaliId = "nonexist"
        every { userRepository.findUserByTaliId(nonExistTaliId) } returns null

        val updatedPhoneNumber = "010-1111-1111"

        // when, then
        assertThrows<UserNotFoundException> {
            userService.updatePhoneNumber(nonExistTaliId, updatedPhoneNumber)
        }
    }

    @Test
    fun `유저의 전화번호를 업데이트 한다`() {
        // given
        val taliId = "gun025bba"
        val sampleUser = userRepository.stubFindByTaliIdReturnSampleUser(taliId)
        userRepository.stubSave()

        val updatedPhoneNumber = "010-1111-1111"

        assertNotEquals(sampleUser.phoneNumber, updatedPhoneNumber)

        // when
        val result = userService.updatePhoneNumber(taliId, updatedPhoneNumber)

        // then
        assertEquals(updatedPhoneNumber, result.phoneNumber)
    }

    private fun UserRepository.stubFindByTaliIdReturnSampleUser(taliId: String): UserEntity {
        val sampleUser = sampleUser(taliId = taliId)
        every { this@stubFindByTaliIdReturnSampleUser.findUserByTaliId(taliId) } returns sampleUser
        return sampleUser
    }

    private fun UserRepository.stubSave() {
        every { this@stubSave.save(any()) } returnsArgument 0
    }
}