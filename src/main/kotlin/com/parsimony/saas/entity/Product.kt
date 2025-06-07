package com.parsimony.saas.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    val summary: String,
    val description: String,
    val websiteUrl: String,
    val logoUrl: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category
)