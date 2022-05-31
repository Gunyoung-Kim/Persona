package com.gunyoung.persona.api

import com.gunyoung.persona.common.config.CommonComponentConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@Import(CommonComponentConfig::class)
@SpringBootApplication
class PersonaApiApplication

fun main(args: Array<String>) {

}
