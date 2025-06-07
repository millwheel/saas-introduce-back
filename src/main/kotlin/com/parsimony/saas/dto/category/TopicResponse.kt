package com.parsimony.saas.dto.category

import com.parsimony.saas.entity.Topic

data class TopicResponse (
    val id: Long,
    val name: String,
    val slug: String
) {
    constructor(topic: Topic) : this (
        id = topic.id,
        name = topic.name,
        slug = topic.slug
    )
}