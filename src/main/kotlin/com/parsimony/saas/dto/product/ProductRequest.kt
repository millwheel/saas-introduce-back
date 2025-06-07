package com.parsimony.saas.dto.product

data class ProductRequest (
    val slug: String,
    val name: String,
    val summary: String,
    val description: String,
    val websiteUrl: String,
)