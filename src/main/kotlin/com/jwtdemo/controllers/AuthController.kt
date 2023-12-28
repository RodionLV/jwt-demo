package com.jwtdemo.controllers


import com.jwtdemo.dto.JwtRequest
import com.jwtdemo.dto.JwtResponse
import com.jwtdemo.dto.UserDto
import com.jwtdemo.exceptions.ApiException
import com.jwtdemo.services.UserService
import com.jwtdemo.utils.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired lateinit var userService: UserService
    @Autowired lateinit var jwtTokenUtil: JwtTokenUtil
    @Autowired lateinit var authenticationManager: AuthenticationManager;

    @PostMapping
    fun registration(@RequestBody authRequest: JwtRequest): ResponseEntity<Any>{
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authRequest.email, authRequest.password))
        }catch ( authenticationException: AuthenticationException ){
            return ResponseEntity( ApiException(HttpStatus.UNAUTHORIZED.value(), "неверный логин или пароль"), HttpStatus.UNAUTHORIZED)
        }
        val user: UserDetails = userService.loadUserByUsername(authRequest.email ?: "")
        val token: String = jwtTokenUtil.generateToken(user)

        return ResponseEntity.ok(JwtResponse(token = token))
    }

    @PostMapping("/login")
    fun login(@RequestBody userDto: UserDto){


    }
    @PostMapping("/logout")
    fun logout(){

    }

    @GetMapping("/access")
    fun access(){

    }


}