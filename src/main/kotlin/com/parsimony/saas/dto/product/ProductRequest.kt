package com.parsimony.saas.dto.product

data class ProductRequest(
    val code: String?,
    val name: String?,
    val summary: String?,
    val description: String?,
    val websiteUrl: String?,
    val topicIds: List<Long>?
)