package com.jwtdemo.repositories

import com.jwtdemo.models.UserModel
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface UserRepository: CrudRepository<UserModel, Long> {
    fun findOneByEmail(email: String): Optional<UserModel>
}