package com.jwtdemo.exceptions

data class ApiException (
    var status: Int? = null,
    var message: String? = null
)