package com.gunyoung.persona.common.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

const val PERSONA_COMMON_BASE_PACKAGE = "com.gunyoung.persona.common"

@Configuration
@Import(
    CommonComponentConfig::class,
    JPAConfig::class,
    RedisConfig::class
)
class CommonConfig
