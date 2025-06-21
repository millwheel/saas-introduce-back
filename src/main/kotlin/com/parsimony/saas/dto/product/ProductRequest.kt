package com.parsimony.saas.dto.product

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class ProductRequest(
    @NotBlank
    val code: String,
    @NotBlank
    val name: String,
    val summary: String,
    val description: String,
    @NotBlank
    val websiteUrl: String,
    @NotEmpty
    val topicIds: List<Long>
)