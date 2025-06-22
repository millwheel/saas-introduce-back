package com.parsimony.saas.dto.product

import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.ProductViewStatistic
import java.time.LocalDateTime

data class ProductResponse(
    val id: Long,
    val code: String,
    val name: String,
    val summary: String?,
    val description: String?,
    val websiteUrl: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val totalViews: Int,
) {
    constructor(product: Product, productViewStatistic: ProductViewStatistic) : this(
        id = product.id,
        code = product.code,
        name = product.name,
        summary = product.summary,
        description = product.description,
        websiteUrl = product.websiteUrl,
        createdAt = product.createdAt,
        updatedAt = product.updatedAt,
        totalViews = productViewStatistic.totalViews
    )
}
