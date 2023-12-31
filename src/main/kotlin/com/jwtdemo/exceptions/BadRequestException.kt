package com.jwtdemo.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class BadRequestException : RuntimeException{
    val code: Int = HttpStatus.BAD_REQUEST.value()
    val status: HttpStatus = HttpStatus.BAD_REQUEST

    constructor() : super()
    constructor(message: String) : super(message)
}