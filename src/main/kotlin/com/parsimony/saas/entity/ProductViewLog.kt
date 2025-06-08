package com.parsimony.saas.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class ProductViewLog(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,

    val userId: String?,

    val ipAddress: String,

    val userAgent: String,

    val viewedAt: LocalDateTime = LocalDateTime.now()
) {

}