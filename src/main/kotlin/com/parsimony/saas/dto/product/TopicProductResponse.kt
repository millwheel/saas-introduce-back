package com.parsimony.saas.dto.product

import com.parsimony.saas.entity.Product

data class TopicProductResponse(
    val id: Long,
    val name: String,
    val code: String,
    val summary: String
) {
    constructor(product: Product): this(
        id = product.id,
        name = product.name,
        code = product.code,
        summary = product.summary
    )
}
