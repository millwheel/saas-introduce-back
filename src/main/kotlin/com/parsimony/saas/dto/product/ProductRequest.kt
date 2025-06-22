package com.parsimony.saas.dto.product

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class ProductRequest(
    @field:NotBlank
    val code: String,

    @field:NotBlank
    val name: String,

    val summary: String?,
    val description: String?,

    @field:NotBlank
    val websiteUrl: String,

    val topicIds: List<Long>?
)