package com.parsimony.saas.dto.product

import com.parsimony.saas.entity.Product

data class TopicProductResponse(
    val id: Long,
    val name: String,
    val slug: String,
    val summary: String
) {
    constructor(product: Product): this(
        id = product.id,
        name = product.name,
        slug = product.slug,
        summary = product.summary
    )
}
