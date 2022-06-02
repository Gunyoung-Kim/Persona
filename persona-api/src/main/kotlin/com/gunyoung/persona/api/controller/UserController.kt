package com.gunyoung.persona.api.controller

import com.gunyoung.persona.common.model.UpdatePhoneNumberRequest
import com.gunyoung.persona.common.model.User
import com.gunyoung.persona.common.model.UserPhoneNumberResponse
import com.gunyoung.persona.common.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/v1/api/users")
class UserController(
    val userService: UserService
) {

    @GetMapping("/{taliId}")
    fun findUserByTaliId(@PathVariable("taliId") taliId: String): User =
        userService.findUserByTaliId(taliId)

    @PutMapping("/phonenumber")
    fun modifyPhoneNumber(updatePhoneNumberRequest: UpdatePhoneNumberRequest): UserPhoneNumberResponse =
        userService.modifyPhoneNumber(
            taliId = updatePhoneNumberRequest.taliId,
            updatedPhoneNumber = updatePhoneNumberRequest.phoneNumber
        ).createPhoneNumberResponse()

    private fun User.createPhoneNumberResponse(): UserPhoneNumberResponse =
        UserPhoneNumberResponse(
            taliId = this.taliId,
            phoneNumber = this.phoneNumber
        )
}