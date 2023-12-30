package com.jwtdemo.services

import com.jwtdemo.dto.JwtResponse
import com.jwtdemo.exceptions.UnauthorizedException
import com.jwtdemo.utils.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service


@Service
class AuthService {

    @Autowired lateinit var authenticationManager: AuthenticationManager
    @Autowired lateinit var jwtTokenUtil: JwtTokenUtil
    @Autowired lateinit var userService: UserService


    fun getToken(email: String?, password: String?): ResponseEntity<JwtResponse>{
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email, password))
        }catch ( authenticationException: AuthenticationException){
            throw UnauthorizedException("неверный логин или пароль")
        }
        val user: UserDetails = userService.loadUserByUsername(email ?: "")
        val token: String = jwtTokenUtil.generateToken(user)

        return ResponseEntity.ok(JwtResponse(token = token))
    }

}