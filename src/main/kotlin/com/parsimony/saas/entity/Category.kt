package com.parsimony.saas.entity

import com.parsimony.saas.dto.category.CategoryCreateRequest
import jakarta.persistence.*

@Entity
class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val slug: String,

    val name: String,

) {
    constructor(request: CategoryCreateRequest): this (
        slug = request.slug,
        name = request.name
    )
}
