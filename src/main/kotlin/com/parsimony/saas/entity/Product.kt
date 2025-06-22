package com.parsimony.saas.entity

import com.parsimony.saas.dto.product.ProductRequest
import com.parsimony.saas.excetion.custom.InvalidInputException
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

    var summary: String?,

    var description: String?,

    var websiteUrl: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToMany
    @JoinTable(
        name = "product_topic",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "topic_id")]
    )
    var topics: MutableSet<Topic>

) {
    constructor(productRequest: ProductRequest, topics: MutableSet<Topic>): this (
        code = productRequest.code ?: throw InvalidInputException("code should not be null"),
        name = productRequest.name ?: throw InvalidInputException("name should not be null"),
        summary = productRequest.summary,
        description = productRequest.description,
        websiteUrl = productRequest.websiteUrl ?: throw InvalidInputException("websiteUrl should not be null"),
        topics = topics
    )

    fun update(productRequest: ProductRequest) {
        code = productRequest.code ?: throw InvalidInputException("code should not be null")
        name = productRequest.name ?: throw InvalidInputException("name should not be null")
        summary = productRequest.summary
        description = productRequest.description
        websiteUrl = productRequest.websiteUrl ?: throw InvalidInputException("websiteUrl should not be null")
        updatedAt = LocalDateTime.now()
    }

    fun updateTopics(topics: MutableSet<Topic>) {
        this.topics = topics
    }
}