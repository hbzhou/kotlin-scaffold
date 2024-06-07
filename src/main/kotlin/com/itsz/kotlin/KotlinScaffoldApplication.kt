package com.itsz.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@SpringBootApplication
class KotlinScaffoldApplication{
	@Bean
	fun userDetailsService(): UserDetailsService {
		val user = User.withUsername("user")
			.password("{noop}pass")
			.authorities("ROLE_ACTIVITI_USER")
			.build()
		return InMemoryUserDetailsManager(user)
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinScaffoldApplication>(*args)
}


