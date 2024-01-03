package com.jwtdemo.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ApiErrorHandler {
    @ExceptionHandler(UnauthorizedException::class)
    fun unauthorizedException(e: UnauthorizedException): ResponseEntity<ApiExceptionResponse>{
        return ResponseEntity(ApiExceptionResponse(e.code, e.status, e.message), e.status)
    }
    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(e: BadRequestException): ResponseEntity<ApiExceptionResponse>{
        return ResponseEntity(ApiExceptionResponse(e.code, e.status, e.message), e.status)
    }
}