package com.parsimony.saas.entity

import com.parsimony.saas.dto.product.ProductRequest
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    var code: String,

    @Column(nullable = false, unique = true)
    var name: String,

    var summary: String,

    var description: String,

    var websiteUrl: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToMany
    @JoinTable(
        name = "product_topic",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "topic_id")]
    )
    val topics: MutableSet<Topic> = mutableSetOf()

) {
    constructor(productRequest: ProductRequest): this (
        code = productRequest.code,
        name = productRequest.name,
        summary = productRequest.summary,
        description = productRequest.description,
        websiteUrl = productRequest.websiteUrl
    )

    fun update(productRequest: ProductRequest) {
        code = productRequest.code
        name = productRequest.name
        summary = productRequest.summary
        description = productRequest.description
        websiteUrl = productRequest.websiteUrl
        updatedAt = LocalDateTime.now()
    }
}