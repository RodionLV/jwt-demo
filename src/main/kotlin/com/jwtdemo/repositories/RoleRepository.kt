package com.jwtdemo.repositories

import com.jwtdemo.models.RoleModel
import org.springframework.data.repository.CrudRepository
import java.util.*

interface RoleRepository : CrudRepository<RoleModel, Long> {
    fun findOneByRole(role: String): Optional<RoleModel>
}