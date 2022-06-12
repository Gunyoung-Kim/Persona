package com.gunyoung.persona.api.controller

import com.gunyoung.persona.api.model.*
import com.gunyoung.persona.common.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/{taliId}")
    fun findUserByTaliId(@PathVariable("taliId") taliId: String): UserResponse =
        userService.findUserByTaliId(taliId)
            .createUserResponse()

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
}
