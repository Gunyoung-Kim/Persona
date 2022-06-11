package com.gunyoung.persona.api.controller

import com.gunyoung.persona.common.service.AlarmOptionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/option/alarm")
class AlarmOptionControllers(
    private val alarmOptionService: AlarmOptionService
) {

    @GetMapping("/sms/{taliId}")
    fun isSmsAlarmReceived(@PathVariable("taliId") taliId: String): Boolean =
        alarmOptionService.isSmsAlarmReceived(taliId)

    @PutMapping("/sms/{taliId}", params = ["isReceived"])
    fun updateSmsAlarmReceived(
        @PathVariable("taliId") taliId: String,
        @RequestParam("isReceived") isReceived: Boolean
    ): Boolean = alarmOptionService.updateSmsAlarmReceived(taliId, isReceived)

    @GetMapping("/mail/{taliId}")
    fun isMailAlarmReceived(@PathVariable("taliId") taliId: String): Boolean =
        alarmOptionService.isMailAlarmReceived(taliId)

    @PutMapping("/mail/{taliId}", params = ["isReceived"])
    fun updateMailAlarmReceived(
        @PathVariable("taliId") taliId: String,
        @RequestParam("isReceived") isReceived: Boolean
    ): Boolean = alarmOptionService.updateMailAlarmReceived(taliId, isReceived)
}