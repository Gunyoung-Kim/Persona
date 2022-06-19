package com.gunyoung.persona.common.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ControllerAdvice

@Configuration
@ComponentScan(
    value = [PERSONA_COMMON_BASE_PACKAGE],
    includeFilters = [
        ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            value = [Controller::class, ControllerAdvice::class, Service::class, Repository::class]
        )
    ],
    excludeFilters = [
        ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            value = [Configuration::class]
        )
    ]
)
class CommonComponentConfig
