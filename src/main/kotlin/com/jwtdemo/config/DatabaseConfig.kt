package com.jwtdemo.config

import com.jwtdemo.models.RoleModel
import com.jwtdemo.models.UserModel
import com.jwtdemo.repositories.RoleRepository
import com.jwtdemo.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DatabaseConfig {

    @Autowired lateinit var userService: UserService
    @Autowired lateinit var roleRepository: RoleRepository

    @Bean
    fun init() = CommandLineRunner{

        roleRepository.save(
            RoleModel(role = "ADMIN")
        )
        roleRepository.save(
            RoleModel(role = "USER")
        )


        userService.saveUser(
            UserModel(
                email = "rodion",
                password = "\$2a\$12\$aq/.rt7Uwov5S1Qdy5drQ.MzOsFkEDd7.JZc8FzZbftJ.jdbSo7ay"
            )
        )


    }

}