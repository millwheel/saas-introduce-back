package com.parsimony.saas.entity

data class TopicQueryModel(
    val id: Long,
    val code: String,
    val name: String,
    val emoji: String,
    val productCount: Long
)