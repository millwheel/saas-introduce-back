package com.parsimony.saas.entity

import com.parsimony.saas.dto.category.CategoryRequest
import jakarta.persistence.*

@Entity
class Topic(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var slug: String,

    var name: String,

    @ManyToMany(mappedBy = "topics")
    val products: MutableSet<Product> = mutableSetOf()

) {
    constructor(request: CategoryRequest): this (
        slug = request.slug,
        name = request.name
    )

    fun update(request: CategoryRequest) {
        slug = request.slug
        name = request.name
    }
}
