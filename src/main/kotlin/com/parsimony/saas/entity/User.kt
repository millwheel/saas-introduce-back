package com.parsimony.saas.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "user_entity")
class User(
    @Id
    val id: String = UUID.randomUUID().toString(),

    val email: String,
    val nickname: String,

    @Enumerated(EnumType.STRING)
    val role: UserRole,

    val createdAt: LocalDateTime = LocalDateTime.now()
)