package com.parsimony.saas.dto.topic

import com.parsimony.saas.entity.TopicQueryModel

data class TopicSummaryResponse (
    val id: Long,
    val name: String,
    val code: String,
    val productCount: Long
) {
    constructor(topic: TopicQueryModel) : this (
        id = topic.id,
        name = topic.name,
        code = topic.code,
        productCount = topic.productCount
    )
}