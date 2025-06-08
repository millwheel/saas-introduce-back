package com.parsimony.saas.dto.product

import com.parsimony.saas.entity.Product
import java.time.LocalDateTime

data class ProductResponse(
    val id: Long,
    val slug: String,
    val name: String,
    val summary: String,
    val description: String,
    val websiteUrl: String,
    val createAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    constructor(product: Product) : this(
        id = product.id,
        slug = product.slug,
        name = product.name,
        summary = product.summary,
        description = product.description,
        websiteUrl = product.websiteUrl,
        createAt = product.createdAt,
        updatedAt = product.updatedAt
    )
}
