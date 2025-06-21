package com.parsimony.saas.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class ProductViewLog(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val productId: Long,

    val productName: String,

    val userId: String?,

    val ipAddress: String,

    val userAgent: String,

    val viewedAt: LocalDateTime = LocalDateTime.now()
) {

}