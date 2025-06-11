package com.parsimony.saas.dto.topic

import com.parsimony.saas.entity.TopicQueryModel

data class TopicSummaryResponse (
    val id: Long,
    val name: String,
    val slug: String,
    val productCount: Long
) {
    constructor(topic: TopicQueryModel) : this (
        id = topic.id,
        name = topic.name,
        slug = topic.slug,
        productCount = topic.productCount
    )
}