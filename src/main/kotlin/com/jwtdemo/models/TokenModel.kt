package com.jwtdemo.models

import jakarta.persistence.*


@Entity
data class TokenModel (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    @Column(nullable = false)
    var refresh: String? = null,

    @OneToOne
    @JoinColumn(name="user_id")
    var user: UserModel? = null,
){
}