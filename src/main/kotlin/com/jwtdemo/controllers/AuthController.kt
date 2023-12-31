package com.jwtdemo.controllers


import com.jwtdemo.dto.JwtRequest
import com.jwtdemo.dto.JwtResponse
import com.jwtdemo.dto.UserDto
import com.jwtdemo.exceptions.ApiException
import com.jwtdemo.exceptions.UnauthorizedException
import com.jwtdemo.models.UserModel
import com.jwtdemo.services.AuthService
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

    @Autowired lateinit var authService: AuthService
    @Autowired lateinit var userService: UserService
    @PostMapping
    fun authenticate(@RequestBody authRequest: JwtRequest): ResponseEntity<JwtResponse>{
        return authService.getToken(authRequest.email, authRequest.password)
    }
    @PostMapping("/registration")
    fun registration(@RequestBody userDto: UserDto): UserDto{
        return userService.saveUser(userDto).toDto()
    }

}