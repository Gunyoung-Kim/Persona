package com.gunyoung.persona.api.controller

import com.gunyoung.persona.common.model.User
import com.gunyoung.persona.common.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/v1/api/users")
class UserController(
    val userService: UserService
) {

    @GetMapping("/{taliId}")
    fun findUserByTaliId(@PathVariable("taliId") taliId : String) : User =
        userService.findUserByTaliId(taliId)

}