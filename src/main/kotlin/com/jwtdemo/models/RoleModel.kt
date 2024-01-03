package com.jwtdemo.models

import jakarta.persistence.*

@Entity
@Table(name = "roles")
data class RoleModel(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    var role: String? = null
)