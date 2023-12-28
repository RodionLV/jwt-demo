package com.jwtdemo.controllers


import com.jwtdemo.dto.UserDto
import com.jwtdemo.exceptions.ConflictException
import com.jwtdemo.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/registration")
    fun registration(@RequestBody userDto: UserDto){

        if(userDto.email == null){
            throw ConflictException("Пользователь не заполнил обезательное поле email")
        }
        if(userDto.password == null){
            throw ConflictException("Пользователь не заполнил обезательное поле password")
        }
        if( userService.findByEmail( userDto.email) != null ){
            throw ConflictException("Пользоваетль с таким email уже существует")
        }

        userService.saveUser( userDto.toModel() )
    }

    @PostMapping("/login")
    fun login(){

    }
    @PostMapping("/logout")
    fun logout(){

    }

    @GetMapping("/access")
    fun access(){

    }


}