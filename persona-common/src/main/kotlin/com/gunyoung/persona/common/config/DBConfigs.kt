package com.gunyoung.persona.common.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericToStringSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import javax.persistence.EntityManager

@Configuration
@EnableRedisRepositories
class RedisConfig {

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Long> =
        RedisTemplate<String, Long>().apply {
            setConnectionFactory(connectionFactory)
            keySerializer = StringRedisSerializer()
            valueSerializer = GenericToStringSerializer(Long::class.java)
        }
}

@Configuration
@EnableJpaRepositories(
    basePackages = [PERSONA_COMMON_BASE_PACKAGE]
)
@EntityScan(PERSONA_COMMON_BASE_PACKAGE)
class JPAConfig(
    val entityManager: EntityManager
) {

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory = JPAQueryFactory(entityManager)
}
