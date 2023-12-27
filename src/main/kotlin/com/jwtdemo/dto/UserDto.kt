package com.jwtdemo.dto

import com.jwtdemo.models.UserModel

data class UserDto (
     val id: Long? = null,
     val email: String? = null,
     val password: String? = null
) {
    fun toModel() = UserModel(id, email, password)
}