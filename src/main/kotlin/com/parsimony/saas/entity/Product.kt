package com.parsimony.saas.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val slug: String,

    val name: String,

    val summary: String,

    val description: String,

    val websiteUrl: String,

    val logoUrl: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToMany
    @JoinTable(
        name = "product_topic",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "topic_id")]
    )
    val topics: MutableSet<Topic> = mutableSetOf()

)