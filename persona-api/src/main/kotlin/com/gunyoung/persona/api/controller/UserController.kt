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