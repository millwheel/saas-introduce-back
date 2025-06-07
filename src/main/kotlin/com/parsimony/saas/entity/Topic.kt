package com.parsimony.saas.entity

import com.parsimony.saas.dto.category.TopicRequest
import jakarta.persistence.*

@Entity
class Topic(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    var slug: String,

    @Column(nullable = false, unique = true)
    var name: String,

    @ManyToMany(mappedBy = "topics")
    val products: MutableSet<Product> = mutableSetOf()

) {
    constructor(request: TopicRequest): this (
        slug = request.slug,
        name = request.name
    )

    fun update(request: TopicRequest) {
        slug = request.slug
        name = request.name
    }
}
