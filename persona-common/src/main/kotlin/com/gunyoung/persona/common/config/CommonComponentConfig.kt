package com.gunyoung.persona.common.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    value = ["com.gunyoung.persona.common"]
)
class CommonComponentConfig