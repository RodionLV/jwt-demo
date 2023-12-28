package com.jwtdemo.dto

data class JwtRequest (
    val email: String? = null,
    val password: String? = null
)