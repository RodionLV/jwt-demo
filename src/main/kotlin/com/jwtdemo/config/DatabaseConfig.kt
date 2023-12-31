package com.jwtdemo.config

import com.jwtdemo.dto.UserDto
import com.jwtdemo.models.RoleModel
import com.jwtdemo.models.UserModel
import com.jwtdemo.repositories.RoleRepository
import com.jwtdemo.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
class DatabaseConfig {

    @Autowired lateinit var userService: UserService
    @Autowired lateinit var roleRepository: RoleRepository

    @Autowired lateinit var passwordEncoder: BCryptPasswordEncoder

    @Bean
    fun init() = CommandLineRunner{

        roleRepository.save(
            RoleModel(role = "ADMIN")
        )
        roleRepository.save(
            RoleModel(role = "USER")
        )

        userService.saveUser(
            UserDto(
                email = "rodion",
                password = "toor"
            )
        )
        userService.saveAdmin(
            UserModel(
                email = "admin",
                password = passwordEncoder.encode("admin")
            )
        )
    }

}