package com.jwtdemo.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.CONFLICT)
class ConflictException : RuntimeException {
    constructor(): super()
    constructor(message: String): super(message)
}