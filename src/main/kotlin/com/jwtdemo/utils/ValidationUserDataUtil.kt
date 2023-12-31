package com.jwtdemo.utils

import com.jwtdemo.exceptions.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component


@Component
class ValidationUserDataUtil {
    fun validatePassword(password: String?){
        if( password == null || password == ""){
            throw BadRequestException("error validation password")
        }
    }

    fun validateEmail(email: String?){
        if( email == null || email == "" ){
            throw BadRequestException("error validation email")
        }
    }
}