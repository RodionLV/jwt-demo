package com.jwtdemo.repositories

import com.jwtdemo.models.TokenModel
import org.springframework.data.repository.CrudRepository

interface TokenRepository : CrudRepository<TokenModel, Long> {
}