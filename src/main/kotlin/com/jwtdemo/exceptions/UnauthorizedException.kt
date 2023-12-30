package com.jwtdemo.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException



@ResponseStatus(HttpStatus.UNAUTHORIZED)
class UnauthorizedException : RuntimeException{
    val code: Int = HttpStatus.UNAUTHORIZED.value()
    val status: HttpStatus = HttpStatus.UNAUTHORIZED

    constructor() : super()
    constructor(message: String) : super(message)
}