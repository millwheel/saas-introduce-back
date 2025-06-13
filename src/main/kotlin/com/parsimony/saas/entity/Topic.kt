package com.parsimony.saas.entity

import com.parsimony.saas.dto.topic.TopicRequest
import jakarta.persistence.*

@Entity
class Topic(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    var code: String,

    @Column(nullable = false, unique = true)
    var name: String,

    @Column(length = 10)
    var emoji: String,

    @ManyToMany(mappedBy = "topics", fetch = FetchType.LAZY)
    val products: MutableSet<Product> = mutableSetOf()

) {
    constructor(request: TopicRequest): this (
        code = request.code,
        name = request.name,
        emoji = request.emoji
    )

    fun update(request: TopicRequest) {
        code = request.code
        name = request.name
        emoji = request.emoji
    }
}
