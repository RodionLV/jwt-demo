package com.jwtdemo.exceptions

import org.springframework.http.HttpStatus

data class ApiException (
    var code: Int? = null,
    var status: HttpStatus? = null,
    var message: String? = null
)