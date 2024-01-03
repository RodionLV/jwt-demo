package com.jwtdemo.exceptions

import org.springframework.http.HttpStatus

data class ApiExceptionResponse (
    var code: Int? = null,
    var status: HttpStatus? = null,
    var message: String? = null
)