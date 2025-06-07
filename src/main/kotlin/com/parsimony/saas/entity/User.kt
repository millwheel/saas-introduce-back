package com.parsimony.saas.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val email: String,
    val nickname: String,

    @Enumerated(EnumType.STRING)
    val role: UserRole,

    val joinedAt: LocalDateTime = LocalDateTime.now()
)