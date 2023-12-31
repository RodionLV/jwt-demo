package com.jwtdemo.exceptions

import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ApiErrorHandler {
    @ExceptionHandler(UnauthorizedException::class)
    fun unauthorizedException(e: UnauthorizedException): ResponseEntity<ApiException>{
        return ResponseEntity(ApiException(e.code, e.status, e.message), e.status)
    }
    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(e: BadRequestException): ResponseEntity<ApiException>{
        return ResponseEntity(ApiException(e.code, e.status, e.message), e.status)
    }
}