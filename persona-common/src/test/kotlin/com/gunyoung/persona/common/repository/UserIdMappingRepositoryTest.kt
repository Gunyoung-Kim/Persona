package com.gunyoung.persona.common.repository

import com.gunyoung.persona.common.SpringBootTestConfig
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(SpringBootTestConfig::class)
class UserIdMappingRepositoryTest(
    @Autowired
    private val userIdMappingRepository: UserIdMappingRepository
) {

    private val taliId = "gun025bba"
    private val id = 1L

    @Disabled
    @Test
    fun `Tali Id 를 통해 Id 를 가져온다`() {
        val result = userIdMappingRepository.findIdByTaliId(taliId)
        println(result)
    }

    @Disabled
    @Test
    fun `Id 를 통해 Tali Id 를 가져온다`() {
        val result = userIdMappingRepository.findTaliIdById(id)
        println(result)
    }

    @Disabled
    @Test
    fun `ID 와 Tali Id 를 저장한다`() {
        userIdMappingRepository.setIdAndTaliId(id, taliId)
    }
}
