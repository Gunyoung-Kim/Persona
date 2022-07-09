package com.gunyoung.persona.common

import com.gunyoung.persona.common.config.CommonConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(CommonConfig::class)
class SpringBootTestConfig
