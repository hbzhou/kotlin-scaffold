package com.itsz.kotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec
import org.springframework.security.web.SecurityFilterChain

class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it.anyRequest().permitAll()
            }
            .formLogin { Customizer.withDefaults<FormLoginSpec>() }
            .httpBasic { Customizer.withDefaults<HttpBasicSpec>() }
            .cors { it.disable()}
            .build()
    }
}