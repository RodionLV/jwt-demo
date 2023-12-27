package com.jwtdemo.models

import com.jwtdemo.dto.UserDto
import jakarta.persistence.*
import kotlin.collections.*
import kotlin.collections.ArrayList

@Entity
data class UserModel(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var email: String? = null,
    @Column(nullable = false)
    var password: String? = null,

    @OneToOne(mappedBy = "user")
    var token: TokenModel? = null,

    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_role")]
    )
    var roles: MutableList<RoleModel> = ArrayList()
) {
    fun toDto() = UserDto(id, email, password)
}