package com.jwtdemo.services

import com.jwtdemo.dto.UserDto
import com.jwtdemo.exceptions.BadRequestException
import com.jwtdemo.models.RoleModel
import com.jwtdemo.models.UserModel
import com.jwtdemo.repositories.RoleRepository
import com.jwtdemo.repositories.UserRepository
import com.jwtdemo.utils.ValidationUserDataUtil
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService : UserDetailsService {
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var roleRepository: RoleRepository
    @Autowired lateinit var validationUserDataUtil: ValidationUserDataUtil
    @Autowired lateinit var passwordEncoder: BCryptPasswordEncoder
    @Transactional
    override fun loadUserByUsername(email: String): UserDetails {
        val user: UserModel = userRepository.findOneByEmail(email).orElseThrow {
            UsernameNotFoundException("Пользователь не найден")
        }

        return org.springframework.security.core.userdetails.User(
            user.email,
            user.password,
            user.roles.stream().map { SimpleGrantedAuthority(it.role) }.toList(),
        )
    }

    fun findByEmail(email: String) : Optional<UserModel> {
        return userRepository.findOneByEmail(email)
    }

    fun saveUser(user: UserDto): UserModel {

        validationUserDataUtil.validateEmail(user.email)
        validationUserDataUtil.validatePassword(user.password)

        if( !userRepository.findOneByEmail(user.email ?: "").isEmpty ){
            throw BadRequestException("Пользователь с данным email уже существует")
        }

        val user: UserModel = UserModel(
            email = user.email,
            password = passwordEncoder.encode(user.password),
            roles = mutableListOf(roleRepository.findOneByRole("USER").get())
        )

        return userRepository.save(user)
    }

    fun saveAdmin(admin: UserModel): UserModel {
        admin.roles.add( roleRepository.findOneByRole("ADMIN").get() )

        return userRepository.save(admin)
    }
}