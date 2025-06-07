package com.parsimony.saas.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class ProductReaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saas_id")
    val product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Enumerated(EnumType.STRING)
    val reactionType: ReactionType,

    val createdAt: LocalDateTime = LocalDateTime.now()
)