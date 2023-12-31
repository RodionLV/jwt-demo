package com.jwtdemo.config

import com.jwtdemo.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig {
    @Autowired lateinit var userService: UserService
    @Autowired lateinit var jwtFilter: JwtFilter
    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain{
        http.authorizeHttpRequests {
            it
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
        }
            .addFilterBefore(jwtFilter, BasicAuthenticationFilter::class.java)
            .csrf { csrf->csrf.disable() }
            .cors { cors->cors.disable() }
            .sessionManagement { session->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .exceptionHandling { it.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.NOT_FOUND)) }


        return http.build()
    }

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider{
        val provider: DaoAuthenticationProvider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder())
        provider.setUserDetailsService(userService)
        return provider
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager{
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder() : BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

}