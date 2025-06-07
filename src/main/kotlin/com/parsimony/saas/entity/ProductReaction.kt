package com.parsimony.saas.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["product_id", "user_id"])])
class ProductReaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Enumerated(EnumType.STRING)
    val reactionType: ReactionType,

    val createdAt: LocalDateTime = LocalDateTime.now()
) {

    companion object {
        fun of(product: Product, user: User, reactionType: ReactionType): ProductReaction {
            return ProductReaction(
                product = product,
                user = user,
                reactionType = reactionType
            )
        }
    }
}