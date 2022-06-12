package com.gunyoung.persona.api.controller

import com.gunyoung.persona.api.model.UpdatePhoneNumberRequest
import com.gunyoung.persona.api.model.UserPhoneNumberResponse
import com.gunyoung.persona.common.model.UserEntity
import com.gunyoung.persona.common.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/{taliId}")
    fun findUserByTaliId(@PathVariable("taliId") taliId: String): UserEntity =
        userService.findUserByTaliId(taliId)

    @GetMapping("/{taliId}/id")
    fun findUserIdByTaliId(@PathVariable("taliId") taliId: String): Long =
        userService.findUserIdByTaliId(taliId)

    @GetMapping("/{userId}/taliId")
    fun findTaliIdByUserId(@PathVariable("userId") userId: Long): String =
        userService.findTaliIdByUserId(userId)

    @PutMapping("/phonenumber")
    fun modifyPhoneNumber(updatePhoneNumberRequest: UpdatePhoneNumberRequest): UserPhoneNumberResponse =
        userService.updatePhoneNumber(
            taliId = updatePhoneNumberRequest.taliId,
            updatedPhoneNumber = updatePhoneNumberRequest.phoneNumber
        ).createPhoneNumberResponse()

    private fun UserEntity.createPhoneNumberResponse(): UserPhoneNumberResponse =
        UserPhoneNumberResponse(
            taliId = this.taliId,
            phoneNumber = this.phoneNumber
        )
}
