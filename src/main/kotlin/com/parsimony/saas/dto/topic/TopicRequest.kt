package com.parsimony.saas.dto.topic

data class TopicRequest (
    val name: String,
    val slug: String,
    val emoji: String
)