package com.parsimony.saas.excetion.handler

data class ErrorResult(
    val status: Int,
    val message: String,
    val path: String? = null,
    val timestamp: String = java.time.LocalDateTime.now().toString(),
)
