package com.parsimony.saas.dto.topic

import com.parsimony.saas.dto.product.TopicProductResponse
import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.Topic

data class TopicResponse (
    val id: Long,
    val name: String,
    val slug: String,
    val emoji: String,
    val products: List<TopicProductResponse>
) {
    constructor(topic: Topic, products: MutableSet<Product>) : this(
        id = topic.id,
        name = topic.name,
        slug = topic.code,
        emoji = topic.emoji,
        products = products.map { TopicProductResponse(it) }
    )
}