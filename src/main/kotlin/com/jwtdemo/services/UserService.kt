package com.jwtdemo.services

import com.jwtdemo.models.RoleModel
import com.jwtdemo.models.UserModel
import com.jwtdemo.repositories.RoleRepository
import com.jwtdemo.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService : UserDetailsService {
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var roleRepository: RoleRepository

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

    @Transactional
    fun saveUser(user: UserModel): UserModel {

        user.roles.add( roleRepository.findOneByRole("USER").get() )

        return userRepository.save(user)
    }
}